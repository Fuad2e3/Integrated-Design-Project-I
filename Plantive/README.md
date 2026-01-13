# Plantive - Plant Care Made Simple

Plantive is an Android application designed to make plant care simple, calm, and mistake-free for beginners and urban gardeners.

## Features

- **Home Dashboard**: Quick overview of plant health and today's tasks
- **Plant Management**: Add and manage your plants with images and care history
- **Smart Reminders**: Never forget to water your plants with intelligent notifications
- **Task Tracking**: Track watering, fertilizing, and other care activities
- **AI Assistant**: Ask questions about plant care in natural language
- **Settings**: Customize notifications, language, and theme

## Project Structure

```
app/
├── src/main/
│   ├── java/com/plantive/
│   │   ├── ui/              # Activities and Fragments
│   │   ├── models/          # Data models (Plant, Task)
│   │   ├── utils/           # Database and utilities
│   │   └── adapters/        # RecyclerView adapters
│   ├── res/
│   │   ├── layout/          # XML layout files
│   │   ├── values/          # String, color, and theme resources
│   │   ├── drawable/        # Drawable resources
│   │   └── menu/            # Menu resources
│   └── AndroidManifest.xml
└── build.gradle.kts
```

## Technology Stack

- **Language**: Java
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)
- **Architecture**: MVVM with LiveData
- **Database**: Room Database
- **UI**: Material Design 3
- **Networking**: Retrofit (for future API integration)
- **Image Loading**: Glide

## Design System

### Colors
- **Primary Green**: Deep green (#1B5E20)
- **Secondary Green**: Light green (#C8E6C9)
- **Alert Red**: #D32F2F
- **Alert Yellow**: #FBC02D
- **Neutral**: Whites and grays

### Typography
- **Font Family**: Sans-serif
- **Large headings** for clear information hierarchy
- **High contrast** for readability

## Getting Started

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the app on an emulator or device (min SDK 24)

## Architecture

### MVVM Pattern
- **Model**: Data classes (Plant, Task) and Room Database
- **View**: Activities and Fragments with XML layouts
- **ViewModel**: LiveData for reactive UI updates

### Key Components

1. **WelcomeActivity**: Onboarding experience
2. **MainActivity**: Main app with bottom navigation
3. **HomeDashboardFragment**: Central hub showing plant status
4. **MyPlantsFragment**: Grid view of all plants
5. **TasksRemindersFragment**: Task management
6. **AIAssistantFragment**: Chat interface
7. **SettingsFragment**: User preferences

## Database Schema

### Plants Table
- id (Primary Key)
- name
- type
- imageUrl
- location
- healthStatus
- dateAdded
- waterFrequencyDays
- fertilizeFrequencyDays

### Tasks Table
- id (Primary Key)
- plantId (Foreign Key)
- taskType
- scheduledDate
- completed
- completedDate

## Future Enhancements

- [ ] Cloud synchronization
- [ ] Weather integration
- [ ] Push notifications
- [ ] Image recognition for plant identification
- [ ] Plant care tips database
- [ ] Community features
- [ ] Plant shop integration

## Development Notes

- All activities and fragments are placeholders with TODO comments
- Adapters need to be implemented for RecyclerViews
- Database operations should use background threads (LiveData/ViewModel)
- Material Design components are used throughout

## License

[Add your license here]

## Contact

[Add contact information]
