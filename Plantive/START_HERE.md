# 🌱 Plantive - START HERE

## Welcome! This is your quick navigation guide.

---

## 📍 Where to Go Based on Your Role

### 🤖 **AI Agent / Copilot**
**Read this first:** [`.github/copilot-instructions.md`](.github/copilot-instructions.md)
- 304 lines of comprehensive architecture guide
- Data models, design system, screen responsibilities
- State management patterns, component library
- Development workflows, styling rules
- Common patterns, debugging tips

### 👨‍💻 **Developer Starting Development**
1. Run the project: `npm install && npm start`
2. Read: [`README.md`](README.md) (project overview)
3. Reference: [`DEVELOPMENT.md`](DEVELOPMENT.md) (workflows & examples)
4. Details: [`.github/copilot-instructions.md`](.github/copilot-instructions.md)

### 🔧 **Contributing Code**
1. Review: [`CONTRIBUTING.md`](CONTRIBUTING.md) (code style, PR process)
2. Check: [`.github/copilot-instructions.md`](.github/copilot-instructions.md) (patterns)
3. Follow: Code style rules, use theme tokens, write TypeScript

### 📚 **Understanding the Architecture**
Read in this order:
1. [`README.md`](README.md) - High-level overview
2. [`.github/copilot-instructions.md`](.github/copilot-instructions.md) - Detailed guide
3. [`src/theme/tokens.ts`](src/theme/tokens.ts) - Design system
4. [`src/store/plantStore.ts`](src/store/plantStore.ts) - State management

### 🎨 **Working with Design/Styling**
1. Reference: [`src/theme/tokens.ts`](src/theme/tokens.ts)
2. Never hardcode colors/spacing - always use tokens
3. Example: [`src/components/Button.tsx`](src/components/Button.tsx)

### 🧩 **Adding New Features**
1. Read: [`DEVELOPMENT.md`](DEVELOPMENT.md) - Has step-by-step examples
2. Reference: [`.github/copilot-instructions.md`](.github/copilot-instructions.md)
3. Use templates from existing screens/components

---

## 📂 Quick File Reference

### Documentation Files
| File | Size | Purpose |
|------|------|---------|
| **`.github/copilot-instructions.md`** | 304 lines | ⭐⭐⭐ **MAIN REFERENCE** - Complete architecture guide |
| `README.md` | 160 lines | Project overview, quick start, features |
| `DEVELOPMENT.md` | 200+ lines | Dev workflows, examples, debugging |
| `CONTRIBUTING.md` | 150+ lines | Code style, branch strategy, PR checklist |
| `PROJECT_SUMMARY.md` | 250+ lines | Complete setup summary |
| `SETUP_COMPLETE.md` | 100+ lines | Project completion details |

### Source Code Key Files
| File | Lines | Purpose |
|------|-------|---------|
| `src/theme/tokens.ts` | 50 | Design system (colors, spacing, typography) |
| `src/store/plantStore.ts` | 100 | Zustand state management with Plant/Task models |
| `src/App.tsx` | 100+ | Navigation root (tabs + stacks) |
| `src/screens/HomeDashboardScreen.tsx` | 120 | Example screen implementation |
| `src/components/Button.tsx` | 60 | Example component with theme usage |

---

## 🎯 The ONE Most Important File

### [`.github/copilot-instructions.md`](.github/copilot-instructions.md)

This is your comprehensive reference for:
- ✅ Complete project architecture
- ✅ Data models with TypeScript interfaces
- ✅ Design system specifications with examples
- ✅ All 5 screen responsibilities
- ✅ State management patterns
- ✅ Component library (props, usage)
- ✅ Development workflows (step-by-step)
- ✅ Styling rules (always use tokens!)
- ✅ Common patterns and solutions
- ✅ Debugging and performance tips

**When in doubt, refer to this file.** 👈

---

## 🚀 Quick Start (3 steps)

```bash
# 1. Install dependencies
cd plantive
npm install

# 2. Start development server
npm start

# 3. Run on your device/emulator
# Press: i (iOS), a (Android), or w (web)
```

---

## 📋 File Organization

```
plantive/
├── .github/
│   └── copilot-instructions.md    👈 START HERE (for AI agents)
├── src/
│   ├── screens/                   # 5 main screens
│   ├── components/                # Button, Card, Badge
│   ├── store/                     # Zustand state (plantStore.ts)
│   ├── theme/                     # Design tokens (tokens.ts)
│   ├── utils/                     # Helper functions
│   └── App.tsx                    # Navigation root
├── README.md                      # Overview
├── DEVELOPMENT.md                 # Developer guide
├── CONTRIBUTING.md                # Code style & PR process
├── PROJECT_SUMMARY.md             # Setup details
└── START_HERE.md                  # This file!
```

---

## 🎨 Design Philosophy

Every file follows this principle:

> "Keep everything **visual, simple, and calm**. When in doubt, remove text and add emoji/color."

---

## ✨ What's Ready to Use

- ✅ 5 complete screens
- ✅ Reusable component library
- ✅ Zustand state management
- ✅ Design system (colors, spacing, typography)
- ✅ TypeScript strict mode
- ✅ Navigation (tabs + stacks)
- ✅ Date utilities
- ✅ ESLint & Prettier configured

---

## 💡 Common Workflows

### "I want to add a new screen"
→ See `DEVELOPMENT.md` section "Adding a New Screen" with step-by-step example

### "I need to change colors"
→ Update `src/theme/tokens.ts` - changes propagate everywhere

### "How do I manage state?"
→ Use `usePlantStore` hook (see `src/store/plantStore.ts` for examples)

### "What's the naming convention?"
→ See `CONTRIBUTING.md` "Naming Conventions" section

### "I don't know where to start"
→ Read [`.github/copilot-instructions.md`](.github/copilot-instructions.md) first!

---

## 🔗 Essential Links

**For AI Agents**: [`.github/copilot-instructions.md`](.github/copilot-instructions.md)  
**For Developers**: [`README.md`](README.md)  
**For Contributors**: [`CONTRIBUTING.md`](CONTRIBUTING.md)  
**For Development Help**: [`DEVELOPMENT.md`](DEVELOPMENT.md)  
**For Setup Info**: [`PROJECT_SUMMARY.md`](PROJECT_SUMMARY.md)

---

## ❓ FAQ

**Q: Where's the comprehensive architecture guide?**  
A: [`.github/copilot-instructions.md`](.github/copilot-instructions.md) (304 lines)

**Q: How do I run the app?**  
A: `npm install && npm start` then press i, a, or w

**Q: Can I hardcode colors?**  
A: No! Import from `src/theme/tokens.ts` instead

**Q: What if I need help with X?**  
A: Check the table above - each doc handles different needs

**Q: Is there a component library?**  
A: Yes! See `src/components/` - Button, Card, Badge, PlantCard

---

## 🎯 Next Steps

1. **Run**: `npm install && npm start`
2. **Read**: [`.github/copilot-instructions.md`](.github/copilot-instructions.md)
3. **Explore**: The code in `src/` (screens, components, store)
4. **Build**: Your next feature following the patterns

---

## 🌱 Remember

This project is **fully scaffolded** with:
- Clean architecture
- Comprehensive documentation
- Best practices throughout
- AI-friendly codebase

No boilerplate confusion. Just pick a feature and start building!

---

**Happy coding! 💚**
