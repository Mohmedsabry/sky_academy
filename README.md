# سنترى (Sentray) — Academy Management App

Mobile app design project for a tutoring/academy management system, split into two apps:

- **Secretary/Admin app** — run by center staff (secretary + admin, same app with role-based permissions)
- **Student app** — used by enrolled students

Language: Arabic (RTL). Font: **Cairo** (Google Font).

---

## Brand identity

- **App name:** سنترى (Sentray)
- **Logo:** wordmark "سنترى" combined with a spiral-style icon, on an olive-green background. Built and finalized in Canva.
  - Canva design ID: `DAHRu57nG-E`
  - Edit link: https://www.canva.com/d/ma7Tzd6aZen3sNj

### Color system — Light mode

| Token | Hex | Use |
|---|---|---|
| `brand/primary-500` | `#A3AF30` | Main brand color (buttons, links, active states) |
| `brand/primary-700` | `#7C8B1E` | Pressed/darker variant |
| `brand/primary-100` | `#E8ECC9` | Light tint (chips, tags) |
| `brand/primary-50` | `#F5F7EA` | Very light surfaces |
| `brand/ink-900` | `#20240F` | Dark surfaces (e.g. QR scanner background) — **not** the earlier `#1E0414`, which read as purple and was rejected |
| `semantic/success` | `#0EA5A4` | Paid / active status (teal, kept distinct from brand green) |
| `semantic/success-bg` | light teal tint | |
| `semantic/warning` | `#F59E0B` | Due soon |
| `semantic/error` | `#DC2626` | Overdue / errors |
| `semantic/info` | `#2F6FED` | Notices (reuses the original brand blue as a secondary accent) |
| Neutrals | grays `#F7F9FC`→`#111827` | Backgrounds, borders, text |

### Color system — Dark mode

Designed for eye comfort: no pure black background, no pure white text, elevation via lightness (not shadows), muted/desaturated semantic accents.

| Token | Hex |
|---|---|
| Background (base) | `#121410` |
| Surface L1 (cards) | `#1B1E17` |
| Surface L2 (elevated/modals) | `#23261E` |
| Border | `#2E3227` |
| Text primary | `#E8EAE2` |
| Text secondary | `#A8AD9C` |
| Text tertiary | `#6E7266` |
| Primary | `#ADBB4A` |
| Primary pressed | `#8A9639` |
| Primary tint (100) | `#262B16` |
| Primary tint (50) | `#1B1F10` |
| Success | `#5BC9A8` · bg `#16332C` |
| Warning | `#E8B84B` · bg `#332A11` |
| Error | `#E8746B` · bg `#351A17` |
| Info | `#6C93E8` · bg `#1A2333` |

---

## Screens

### Secretary/Admin app
| Screen | Status |
|---|---|
| Login | ✅ built |
| Dashboard (stats, quick actions, today's sessions) | ✅ built |
| Students List (search, filters, status badges) | ✅ built |
| Attendance — Select Session | ✅ built |
| QR Scanner (session-scoped) | ✅ built |
| Attendance — Manual Entry (search + confirm one-by-one, for students without a phone) | ⬜ pending |
| Group Details | ⬜ pending |
| Receive Payment | ⬜ pending |
| Finance Overview | ⬜ pending |
| More (Profile/Settings + Logout only) | ⬜ pending |

### Student app
| Screen | Status |
|---|---|
| Login | ⬜ pending |
| Home (next session, attendance %, payment status) | ⬜ pending |
| Schedule / Groups | ⬜ pending |
| Attendance history | ⬜ pending |
| Payments / subscription status | ⬜ pending |
| Content library (videos/PDFs) | ⬜ pending |

---

## Key flow logic

**Attendance / QR:**
- The QR code is **scoped to the session**, not a static per-student badge — a scan is only valid against the currently-selected session.
- Two ways to mark attendance after selecting a session:
  1. **Scan QR** (built)
  2. **Manual entry** — search student by name/code, confirm presence one at a time (fallback for students without a phone)

---

## Where things live

- **Figma design file** (source of truth, uses bound Variables so recoloring cascades automatically):
  - File key: `nIeRSYGf9e2ywstwPNNOfc`
  - Pages: 🎨 Design System · 🔵 Secretary App · 🟢 Student App
  - Variable collection: "Sky Academy Colors" (Light mode built; Dark mode token set approved, not yet added)
  - Note: Figma plan is Starter tier — MCP tool-call limits have been hit repeatedly; upgrading the plan is a manual step on figma.com.
- **Figma Make** — user is iterating on a parallel version there directly (no API access from this side); fixes are communicated as copy-paste prompts.

## Open items
1. Add Dark mode as a second mode on the Figma variable collection.
2. Build remaining Secretary app screens (manual attendance entry, group details, payments, finance, more).
3. Build the full Student app.
4. Reposition Secretary app screens into a proper side-by-side grid (documented convention: 470px column spacing, 950px row spacing).
