# Intellipaat Assignment

## 1. Architecture

The app uses a Clean architecture with MVVM design with Jetpack Compose. Screens render `UiState`, ViewModels handle screen actions and state, and repositories/data sources isolate API access from domain models and use cases. This keeps UI, business rules, and networking easier to change and test independently.

## 2. Offline Support

Offline persistence is not implemented yet. Courses are loaded from the API, and lesson completion currently lives in the ViewModel, so it is lost when the process ends. For offline support, I would add a Room database as the local source of truth, load cached courses immediately, and sync changes with the API when connectivity returns.

## 3. Security

In production, I would store authentication tokens in encrypted storage backed by Android Keystore (for example, encrypted DataStore or EncryptedSharedPreferences), use short-lived access tokens with refresh-token rotation, and never log credentials or tokens.

## 4. Scale

For one million users and hundreds of courses, I would:

- Add server-side pagination, filtering, and stable sorting for course lists.
- Cache course data locally and use background sync for progress updates.
- Scale the API with stateless services, a load balancer, and database indexes/caching.
- Add authorization, rate limiting, monitoring, and structured error reporting.
- Add dependency injection and broader unit, integration, and load testing.

## 5. For iOS Platform

I would build the UI with SwiftUI and use MVVM with async/await. A repository would call the same backend through `URLSession`, persist course data and progress with SwiftData (or Core Data), and store tokens in Keychain. The shared API contract and business rules would keep Android and iOS behavior aligned.
