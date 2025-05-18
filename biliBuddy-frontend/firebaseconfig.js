import {initializeApp} from "firebase/app";
import {getAuth} from "firebase/auth";

const firebaseConfig = {
  apiKey: "AIzaSyDcFHMG-1zRtwtA4dScqnoZjjCOYe-6Ppw",
  authDomain: "bilibuddy-firebase.firebaseapp.com",
  projectId: "bilibuddy-firebase",
  storageBucket: "bilibuddy-firebase.firebasestorage.app",
  messagingSenderId: "206337461794",
  appId: "1:206337461794:web:3e23e7a1fd618d51f2447b",
  measurementId: "G-1J2DN4086X"
};

const firebaseApp = initializeApp(firebaseConfig);
const auth = getAuth(firebaseApp);

export {auth};

