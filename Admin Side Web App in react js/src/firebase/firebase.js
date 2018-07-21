import firebase from 'firebase/app';
import 'firebase/auth';
import 'firebase/database';

const prodConfig = {
    apiKey: 'AIzaSyDf-lMeY8wovDB7_KML8shADKV2Em0xCe8',
    authDomain: 'authentication-4bb8f.firebaseapp.com',
    databaseURL: 'https://authentication-4bb8f.firebaseio.com',
    projectId: 'authentication-4bb8f',
    storageBucket: 'authentication-4bb8f.appspot.com',
    messagingSenderId: '345875020786'
};

const devConfig = {
    apiKey: 'AIzaSyDf-lMeY8wovDB7_KML8shADKV2Em0xCe8',
    authDomain: 'authentication-4bb8f.firebaseapp.com',
    databaseURL: 'https://authentication-4bb8f.firebaseio.com',
    projectId: 'authentication-4bb8f',
    storageBucket: 'authentication-4bb8f.appspot.com',
    messagingSenderId: '345875020786'
};

const config = process.env.NODE_ENV === 'production'
    ? prodConfig
    : devConfig;

if (!firebase.apps.length) {
    firebase.initializeApp(config);
}

const db = firebase.database();
const auth = firebase.auth();

export {
    db,
    auth,
};