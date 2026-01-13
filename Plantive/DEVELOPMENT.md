# Plantive Development Guide

## Quick Start

```bash
cd plantive
npm install
npm start
```

Then choose your platform:
- Press `i` for iOS
- Press `a` for Android
- Press `w` for web

## Project Philosophy

**Plantive** follows a "Zero Thinking" design philosophy:
- Users understand what to do in 5 seconds
- Visual communication > text
- One action per screen
- Soft, natural, calm interface

## Code Organization

### Screens (User-facing pages)
- **WelcomeScreen** - Onboarding with benefit cards
- **HomeDashboardScreen** - Status overview & quick actions
- **MyPlantsScreen** - Grid view of all plants with filters
- **PlantDetailsScreen** - Full plant info & care actions
- **TasksScreen** - Today/Upcoming task management

### Components (Reusable UI)
- **Button** - Primary/secondary/ghost variants with sizes
- **Card** - Container with optional elevation
- **PlantCard** - Compact plant preview for grids
- **HealthBadge** - Color-coded health status indicator
- **IconText** - Icon + text pair for list items

### Store (State)
- **plantStore.ts** - Single Zustand store for all data
  - Plants array with full metadata
  - Tasks array linked to plants
  - Queries: `getTodaysTasks()`, `getUpcomingTasks()`, `getPlantById()`

### Theme (Design System)
- **tokens.ts** - Centralized colors, spacing, typography
  - Always use these constants, never hardcode
- **index.ts** - Theme export

## Adding Features

### Example: Add "Notes" Editing to Plant Details

1. **Update store** if needed (Plant already has `notes` field)
2. **Create/update component** `EditNotesModal` in `src/components/`
3. **Add to PlantDetailsScreen** with button to trigger modal
4. **Call store update** when saving: `usePlantStore.getState().updatePlant(id, { notes: newNotes })`

### Example: Add Watering Reminder Logic

1. In **PlantDetailsScreen**, calculate days until watering:
   ```typescript
   const daysUntilWater = Math.ceil(
     (new Date(plant.lastWatered).getTime() + plant.wateringFrequencyDays * 86400000 - new Date().getTime()) / 86400000
   );
   ```
2. Show in timeline section (already done in example)
3. Create task in store when due

## Styling Best Practices

❌ **WRONG:**
```typescript
<View style={{ paddingLeft: 16, backgroundColor: '#2D5016' }}>
```

✅ **RIGHT:**
```typescript
import { Colors, Spacing } from '@theme/tokens';

<View style={{ paddingLeft: Spacing.md, backgroundColor: Colors.primary }}>
```

## Navigation Flow

```
Welcome (first time)
  ├── Get Started → App Tabs
  └── Login → App Tabs

App Tabs (Main)
  ├── Home (Dashboard)
  │   └── Link to Plants tab & Tasks tab
  ├── Plants (Grid)
  │   └── Plant → Details Screen
  └── Tasks (Today/Upcoming)
```

## Key Data Flows

### Plant Creation Flow
1. MyPlantsScreen → "Add Plant" button
2. (Future: Modal form)
3. Call: `usePlantStore.getState().addPlant({...})`
4. Plant auto-gets ID, appears in grid

### Daily Task Flow
1. Task created when plant needs watering/fertilizing
2. Task due date = last watering date + frequency days
3. TasksScreen.getTodaysTasks() filters for today
4. User taps → completes task
5. Health status updates (future: based on task completion)

### Plant Health Status
Currently set manually:
- Green = healthy
- Yellow = warning
- Red = urgent

Future: AI analysis from plant photos.

## Testing the App

1. **Sample Data**: Modify `App.tsx` to pre-populate store with test plants
2. **Navigation**: Test all tab transitions and stack navigation
3. **Responsive**: Check on different device sizes via emulator
4. **Offline**: Data persists in Zustand (no sync yet)

## Browser DevTools (Web)

When running `npm run web`:
- React DevTools browser extension shows component tree
- Redux DevTools can monitor Zustand state (advanced)
- Native debugger in browser console

## Common Errors & Solutions

| Error | Solution |
|-------|----------|
| "Plant not found" in Details screen | Ensure plantId is correctly passed from MyPlantsScreen |
| Styles not applying | Check you're using theme tokens, not hardcoded values |
| Navigation not triggering | Verify screen is added to Stack/Tab Navigator in App.tsx |
| Store data lost on reload | Expected - no persistence layer yet (can add AsyncStorage) |

## Performance Tips

- Zustand is very fast (no redux boilerplate)
- Avoid mapping large task lists (pagination future enhancement)
- Use `React.memo()` for expensive components if needed
- ProfileScreen not yet implemented

## Future Enhancements Priority

1. **High**: Plant image capture & upload
2. **High**: Notifications for task reminders
3. **Medium**: Weather API for dashboard
4. **Medium**: Plant recommendations based on health trends
5. **Low**: Social sharing, photo timeline

## Resources

- [Expo Documentation](https://docs.expo.dev/)
- [React Navigation](https://reactnavigation.org/)
- [Zustand GitHub](https://github.com/pmndrs/zustand)
- [React Native StyleSheet](https://reactnative.dev/docs/stylesheet)
