# Firebase Custom Token Sign-In without Client App 

The purpose of this project is to build a very simple REST Spring Boot application in order to:
- Generate an idToken without having to pass through a client app
- Check if a token is valid or not

## Why

While working on app project that uses Firebase to authenticate users, I was always struggling to have an idToken in order to test my REST API server (since our APIs calls requires a bearer token).
So this project aims to:
- Do integration tests where you rely on idToken for security
- Develop your Java Firebase server without having a client app to sign in your custom tokens

## How

- Download the latest version from the Release section
- Execute the generated JAR with `java -jar -Dfirebase.config.api-key=<your-api-key> -Dfirebase.config.database-url=<your-firebase-database-url> -Dfirebase.config.service-account-path=<path-to-your-service-account-JSON-file> firebase-custom-token-sign-in-<version>.jar` where:
  - _your-api-key_ is the Google Cloud API Key for your Firebase project
  - _your-firebase-database-url_ is the URL of your Firebase URL
  - _path-to-your-service-account-JSON-file_ is the path to your Json service account
  - _version_ is the version of downloaded JAR

## Endpoints

- `GET /idToken/generate` with the followings required request param:
  - `customUID` String, your test custom ID
  - `returnSecureToken` boolean
  - `mail` String
- `POST /idToken/validate`, with the following required request body:
  - the string of the ID Token
- More to come
## Donate

If you liked the project and find it usefull, why not offering a small cup of coffee? :) [![Donate](https://img.shields.io/badge/Donate-PayPal-green.svg)](https://paypal.me/michelebergia)
