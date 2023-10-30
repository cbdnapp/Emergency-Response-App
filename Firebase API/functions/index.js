/**
 * Import function triggers from their respective submodules:
 *
 * const {onCall} = require("firebase-functions/v2/https");
 * const {onDocumentWritten} = require("firebase-functions/v2/firestore");
 *
 * See a full list of supported triggers at https://firebase.google.com/docs/functions
 */

// const {onRequest} = require("firebase-functions/v2/https");
// const logger = require("firebase-functions/logger");

// Create and deploy your first functions
// https://firebase.google.com/docs/functions/get-started

// exports.helloWorld = onRequest((request, response) => {
//   logger.info("Hello logs!", {structuredData: true});
//   response.send("Hello from Firebase!");
// });

const functions = require('firebase-functions');
const admin = require('firebase-admin');
const express = require('express');
const cors = require('cors');
const app = express();

//POST FUNCTIONS
app.post('/emergencies', (req, res) => {
    return res.status(200).send('Recieved request to report/intialize emergency');
});

app.post('/trucks', (req, res) => {
    return res.status(200).send('Recieved request to create a fire truck');
});

app.post('/firefighters', (req, res) => {
    return res.status(200).send('Recieved request to create a firefighter');
});

app.post('/stations', (req, res) => {
    return res.status(200).send('Recieved create a firestation');
});

//PUT FUNCTIONS
app.put('/emergencies/:emergency_id', (req, res) => {
    return res.status(200).send('Recieved request for requesting backup, adding report details, and changing resolve status. Got parameter emergency_id: ' + req.params.emergency_id);
});

app.put('/emergencies/:emergency_id/trucks/:truck_id', (req, res) => {
    return res.status(200).send('Recieved request for a truck to respond to emergency. Got parameters emergency_id:' + req.params.emergency_id + ' and truck_id: ' + req.params.truck_id);
});

app.put('/stations/:station_id/firefighters/:firefighter_id', (req, res) => {
    return res.status(200).send('Recieved request to add an on duty firefighter. Got parameters station_id: ' + req.params.station_id + ' and firefighter_id: ' + req.params.firefighter_id);
});

app.put('/stations/:station_id/trucks/:truck_id', (req, res) => {
    return res.status(200).send('Recieved request to add a truck to a firestation. Got parameters station_id: ' + req.params.station_id + ' and truck_id: ' + req.params.truck_id);
});


exports.app = functions.https.onRequest(app);