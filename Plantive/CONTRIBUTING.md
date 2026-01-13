# Plantive Contributing Guide

## Getting Started

1. **Clone and install**:
   ```bash
   cd plantive
   npm install
   ```

2. **Start development**:
   ```bash
   npm start
   ```

## Code Style

### TypeScript
- Use strict mode (enabled in `tsconfig.json`)
- No `any` types - define interfaces
- Use meaningful variable names

### Components
- Functional components with hooks
- Extract UI into small, reusable components
- Props should have clear types

### Styling
- **Always** use theme tokens from `src/theme/tokens.ts`
- No magic numbers for spacing, colors, or sizes
- Use `StyleSheet.create()` for better performance

### Naming Conventions
- Screens: `ScreenNameScreen.tsx` (e.g., `PlantDetailsScreen.tsx`)
- Components: `ComponentName.tsx` (e.g., `Button.tsx`)
- Store: `entityStore.ts` (e.g., `plantStore.ts`)
- Types: Define in same file or `types.ts`

## File Structure

```
src/
├── screens/          # Full-page components
├── components/       # Reusable UI components
├── store/           # Zustand state management
├── theme/           # Design tokens
├── navigation/      # Navigator setup (if extracted)
├── utils/           # Helper functions
└── types.ts         # Global types (optional)
```

## Branch Strategy

- `main` - Production-ready code
- `develop` - Integration branch
- `feature/` - Feature branches (e.g., `feature/add-plant-form`)
- `fix/` - Bug fix branches (e.g., `fix/task-due-date-calculation`)

## PR Checklist

Before submitting a pull request:

- [ ] Code follows style guide
- [ ] All theme tokens used (no hardcoded values)
- [ ] TypeScript compiles without errors (`npm run type-check`)
- [ ] Linting passes (`npm run lint`)
- [ ] Tested on iOS/Android or web
- [ ] Updated `.github/copilot-instructions.md` if architecture changed
- [ ] README updated if adding new major features

## Common Development Tasks

### Adding a New Screen

1. Create `src/screens/NewScreen.tsx`:
```typescript
import React from 'react';
import { View, Text, SafeAreaView } from 'react-native';
import { Colors, Spacing } from '@theme/tokens';

interface NewScreenProps {
  onNavigateTo?: (screenName: string) => void;
}

export const NewScreen: React.FC<NewScreenProps> = ({ onNavigateTo }) => {
  return (
    <SafeAreaView style={{ flex: 1, backgroundColor: Colors.background }}>
      {/* Screen content */}
    </SafeAreaView>
  );
};
```

2. Export in `src/screens/index.ts`
3. Add to navigation in `src/App.tsx`

### Adding a New Component

1. Create `src/components/NewComponent.tsx`
2. Use theme tokens and proper TypeScript typing
3. Export in `src/components/index.ts`
4. Document props in JSDoc comments

### Modifying Store

1. Edit `src/store/plantStore.ts`
2. Add type definitions for new data
3. Add getter/setter methods
4. Use Zustand's `set()` and `get()` properly

## Testing Guidelines

- Manual testing on device/emulator is primary method
- Test all navigation paths
- Verify data persistence (Zustand state)
- Check responsive design on different screen sizes

## Documentation

- Keep `README.md` updated with major changes
- Update `.github/copilot-instructions.md` if patterns change
- Add inline comments for complex logic
- Document non-obvious design decisions

## Commit Message Format

```
type(scope): subject

body (optional)

footer (optional)
```

Types: `feat`, `fix`, `docs`, `style`, `refactor`, `test`, `chore`

Examples:
- `feat(store): add plant deletion method`
- `fix(home): correct task count calculation`
- `docs: update styling guidelines`

## Performance Considerations

- Zustand batches updates efficiently
- Use selective subscriptions: `usePlantStore((state) => state.plants)`
- Avoid creating new objects in render
- Consider `React.memo()` for expensive components

## Debugging

1. **React Native Debugger**: `npm start` → options menu
2. **Console logs**: Use `console.log()` for debugging
3. **Redux DevTools**: Can monitor Zustand with setup
4. **Network**: Check API calls in React Native Inspector

## Questions?

Refer to:
- `.github/copilot-instructions.md` - Comprehensive architecture guide
- `README.md` - Project overview
- `DEVELOPMENT.md` - Dev workflow guide
- Existing code patterns - Follow established conventions

---

Thank you for contributing to Plantive! Keep it simple, visual, and calm. 🌱
