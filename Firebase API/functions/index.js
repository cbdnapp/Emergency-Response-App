/**
 * Import function triggers from their respective submodules:
 *
 * const {onCall} = require("firebase-functions/v2/https");
 * const {onDocumentWritten} = require("firebase-functions/v2/firestore");
 *
 * See a full list of supported triggers at https://firebase.google.com/docs/functions
 */

const functions = require('firebase-functions');
const admin = require('firebase-admin');

var serviceAccount = require("./permissions.json");

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});


const express = require('express');
const cors = require('cors');
const { document } = require('firebase-functions/v1/firestore');
const app = express();
const db = admin.firestore();
app.use(cors( {origin:true}));

//POST FUNCTION
app.post('/reports', async (req, res)=>{
    
        try{
            
            await db.collection('reports').add({
                finalized: req.body.finalized,
                author: req.body.author,
                datetimeDispatch: req.body.datetimeDispatch,
                datetimeArrival: req.body.datetimeArrival,
                datetimeReturn: req.body.datetimeReturn, 
                emergencyCode: req.body.emergencyCode, 
                respondingTruck: req.body.respondingTruck,
                commandingOfficer: req.body.commandingOfficer,
                victimCount: req.body.victimCount,
                victimInfo:  req.body.victimInfo,
                policePresent: req.body.policePresent,
                ambulancePresent: req.body.ambulancePresent,
                electricCompanyPresent: req.body.electricCompanyPresent,
                transitPolicePresent: req.body.transitPolicePresent,
                location: req.body.location, 
                notes: req.body.notes
            })
            .then((entity) =>{
                return res.status(201).json({"id": entity.id});
            });

        }catch(error){
            console.log(error);
            return res.status(500).send(error);
        }
    
});

//Put Routes
//Put Routes must replace all properties (stored as arrays) of a document
//Request bodies must have all report properties included
function put_report(req){
    var updated_properties = {};
    Object.keys(req.body).map((property)=>{
        updated_properties[property] = req.body[property];
    });
    return updated_properties;
}

app.put('/reports/:report_id', async (req, res)=>{
    
    try{
        if(
            req.body.finalized === undefined ||
            req.body.author === undefined ||
            req.body.datetimeDispatch === undefined ||
            req.body.datetimeArrival === undefined ||
            req.body.datetimeReturn === undefined ||
            req.body.emergencyCode === undefined ||
            req.body.respondingTruck === undefined ||
            req.body.commandingOfficer === undefined ||
            req.body.victimCount === undefined ||
            req.body.victimInfo === undefined ||
            req.body.policePresent === undefined ||
            req.body.ambulancePresent === undefined ||
            req.body.electricCompanyPresent === undefined ||
            req.body.transitPolicePresent === undefined ||
            req.body.location === undefined ||
            req.body.notes === undefined ||
            Object.keys(req.body).length !== 16
        ){
            return res.status(304).json({"Error": "Invalid request body"})
        } else {
            const reportRef = db.collection('reports').doc(req.params.report_id);
            var updated_report = put_report(req);
            await reportRef.update(updated_report)
            .then(()=>{
                return res.status(200).json({"id": req.params.report_id});
            })
        }
    }catch(error){
        return res.status(500).send(error);
    }
});


//Patch Routes
//Patching functions will add values to a property array without replacing or deleting previously store properties
//We must validate that the properties supplied are included in the reports properties 

//helper function to check if properties keys stored in the request body match the property keys of a report
function valid_properties(req, properties){
    var valid = true;
    Object.keys(req.body).map((property)=>{
        if(!properties.includes(property)){
            valid = false;
        }
    });
    return valid;
}

function patch_report(req, report){
    var updated_report = {};
    Object.keys(req.body).map((property)=>{
        if(property !== 'finalized'){
            updated_report[property] = report[property];
            updated_report[property].push( req.body[property][0]);
        } 
    });
    return updated_report;
}

app.patch('/reports/:report_id', async (req, res) =>{
    try{
        const reportRef = db.collection('reports').doc(req.params.report_id);
        const report = (await reportRef.get()).data();
        
        if(report && valid_properties(req, Object.keys(report))){
            const updated_report = patch_report(req, report);

            await reportRef.update(updated_report)
            .then(()=>{
                return res.status(200).json({"id": req.params.report_id});
            })
        } else {
            return res.status(304).json({"Error": "Invalid request body"})
        }
    }catch(error){
        return res.status(500).send(error);
    }
});

//Get routes

//Get report by ID
app.get('/reports/:report_id', async(req, res) =>{
    try{
        const reportRef = db.collection('reports').doc(req.params.report_id);
        const report = (await reportRef.get()).data();
        
        if(report && valid_properties(req, Object.keys(report))){
                return res.status(200).json(report);
        } else {
            return res.status(400).json({"Error": "Invalid request body"})
        }
    }catch(error){
        return res.status(500).send(error);
    }
});

//Get all reports
app.get('/reports', async(req, res) =>{
    try{
        const reportRef = await db.collection('reports').get();
        const reports = (await reportRef).docs.map(doc => doc.data());
        
        if(reportRef){
                return res.status(200).json(reports)
        } else {
            return res.status(400).json({"Error": "Invalid request body"})
        }
    }catch(error){
        return res.status(500).send(error);
    }
});

//Get all reports based off the "finalized property"

exports.app = functions.https.onRequest(app);