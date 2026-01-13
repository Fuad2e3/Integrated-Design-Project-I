# Plantive - Plant Care Made Simple

![Status](https://img.shields.io/badge/Status-Active%20Development-green)
![React Native](https://img.shields.io/badge/React%20Native-0.73-blue)
![Expo](https://img.shields.io/badge/Expo-50.0-blue)
![TypeScript](https://img.shields.io/badge/TypeScript-5.3-blue)

Plantive is a mobile app that makes plant care simple, calm, and mistake-free for beginners and urban gardeners. The app uses a "zero thinking" design philosophy—users understand what to do instantly through visual cues, minimal text, and one action per screen.

## ✨ Key Features

- 🌱 **Plant Management**: Add and track all your plants with ease
- 📅 **Smart Reminders**: Get notified when plants need watering or fertilizing
- 🎨 **Visual Health Status**: Color-coded indicators (green/yellow/red) show plant health at a glance
- 📊 **Dashboard Overview**: See your garden's overall status in seconds
- 🏡 **Smart Filtering**: View plants by location (indoor/outdoor) or health status
- 🎯 **Simple Interface**: No confusing menus—just tap what you need

## 🚀 Quick Start

### Prerequisites

- Node.js 16+ and npm
- iOS Simulator (Mac) or Android Emulator

### Installation

```bash
cd plantive
npm install
npm start
```

Choose platform:
- `i` for iOS
- `a` for Android  
- `w` for web

## 📁 Project Structure

```
plantive/
├── src/
│   ├── screens/          # 5 main screens
│   │   ├── WelcomeScreen.tsx
│   │   ├── HomeDashboardScreen.tsx
│   │   ├── MyPlantsScreen.tsx
│   │   ├── PlantDetailsScreen.tsx
│   │   └── TasksScreen.tsx
│   ├── components/       # Reusable UI components
│   │   ├── Button.tsx
│   │   ├── Card.tsx
│   │   └── Badge.tsx
│   ├── store/            # Zustand state management
│   │   └── plantStore.ts
│   ├── theme/            # Design system
│   │   ├── tokens.ts
│   │   └── index.ts
│   ├── utils/            # Helper functions
│   └── App.tsx           # Navigation & main logic
├── .github/
│   └── copilot-instructions.md  # 👈 Comprehensive AI development guide
├── DEVELOPMENT.md        # Developer workflow
├── CONTRIBUTING.md       # Contribution guidelines
├── package.json
├── tsconfig.json
└── app.json              # Expo config
```

## 🎨 Design System

All styling uses **design tokens** from `src/theme/tokens.ts` - never hardcode colors!

**Colors**:
- Primary: #2D5016 (Deep green)
- Secondary: #7CB342 (Light green)
- Healthy: #4CAF50 | Warning: #FFC107 | Urgent: #F44336

**Spacing**: xs (4) to 2xl (48)  
**Typography**: 7 sizes from xs (12px) to 3xl (32px)

## 🧠 State Management

Uses **Zustand** for simple, lightweight global state:

```typescript
import { usePlantStore } from '@store/plantStore';

const plants = usePlantStore((state) => state.plants);
usePlantStore.getState().addPlant({ /* ... */ });
```

**Key Methods**:
- `addPlant()`, `updatePlant()`, `removePlant()`
- `addTask()`, `completeTask()`, `rescheduleTask()`
- `getTodaysTasks()`, `getUpcomingTasks()`, `getPlantById()`

## 🧭 Navigation

Bottom tabs (Home, Plants, Tasks) with stack navigation for details:
- Home → Links to other sections
- Plants → Grid → Plant Details
- Tasks → Today/Upcoming tabs

## 📚 Documentation

| File | Purpose |
|------|---------|
| **`.github/copilot-instructions.md`** | **START HERE** - Comprehensive AI development guide |
| `README.md` | Project overview (this file) |
| `DEVELOPMENT.md` | Developer workflows, examples, debugging tips |
| `CONTRIBUTING.md` | Code style, branch strategy, PR process |
| `SETUP_COMPLETE.md` | Project creation summary |

## 💻 Development

```bash
# Start development
npm start

# Type checking
npm run type-check

# Linting
npm run lint
```

**Key Rules**:
- ✅ Use theme tokens (no hardcoded colors/spacing)
- ✅ TypeScript strict mode enforced
- ✅ Functional components with hooks
- ✅ One action per screen (UX principle)

## 🚀 Building for Production

```bash
# Build for iOS
eas build --platform ios

# Build for Android
eas build --platform android

# Web
npm run web
```

## 📋 Project Status

- ✅ Core architecture complete
- ✅ 5 main screens with navigation
- ✅ Zustand state management
- ✅ Design system with tokens
- ⏳ Plant form/edit screen
- ⏳ Image uploads
- ⏳ Push notifications
- ⏳ Weather API integration

## 🤝 Contributing

See `CONTRIBUTING.md` for code style, branch strategy, and PR process.

**Quick checklist**:
- [ ] Use theme tokens (never hardcode colors)
- [ ] Follow TypeScript strict mode
- [ ] Test on device/emulator
- [ ] ESLint/Prettier pass
- [ ] Update docs if changing architecture

## 🎯 Design Philosophy

**"Zero Thinking" UX**:
- ✨ Users understand what to do in 5 seconds
- 🎨 Visual (color, emoji) over text
- 🎯 One action per screen
- 🌿 Soft, calm interface

**When in doubt**: Remove text, add emoji/color, simplify.

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| Navigation fails | Verify screen added to Stack/Tab in App.tsx |
| Styles wrong | Check using theme tokens from `src/theme/tokens.ts` |
| State not updating | Use Zustand selector: `usePlantStore((state) => state.property)` |
| Plant not found | Verify plantId passed from parent screen |

See `DEVELOPMENT.md` for more tips.

## 📖 Resources

- [Expo Docs](https://docs.expo.dev/)
- [React Navigation](https://reactnavigation.org/)
- [Zustand](https://github.com/pmndrs/zustand)
- [TypeScript Handbook](https://www.typescriptlang.org/docs/)

---

**Getting Started?** Read `.github/copilot-instructions.md` for comprehensive development guidance! 🌱

MIT
