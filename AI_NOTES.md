# AI Usage Notes

## Overview

I used AI (mainly ChatGPT) the way I'd use a senior teammate sitting next to me — someone to bounce ideas off, sanity-check decisions, and speed up the boring parts. It wasn't writing the project for me; I was still the one deciding what "correct" looked like, typing the final code, running it, and fixing it when it broke.

Every line in the repo was compiled, run, and tested by me before it made it into the submission. AI suggestions that didn't hold up — or didn't fit the way I'd structured the project — got reworked or thrown out.

---

## 1. Which parts were AI-assisted vs. written by me

**Written by me, end to end:**
- The actual layered architecture (Controller → Service → ServiceImpl), the package structure, and how the pieces connect.
- All final business logic in `ExpenseServiceImpl` — filtering, searching, summary math, monthly aggregation.
- The custom exceptions, the global exception handler, and the response wrapper.
- All test code — I wrote the assertions myself based on what I actually wanted the API to guarantee, not what a generated test happened to check.

**Where AI genuinely helped:**
- **Talking through architecture** — I used it as a sounding board for how to keep controllers thin and push logic into the service layer, and to sanity-check REST naming conventions before I committed to them.
- **Unsticking bugs faster** — Maven dependency clashes, a Spring Boot version mismatch, MockMvc setup that wasn't wiring up correctly, and a couple of Docker build failures. In each case AI helped me narrow down *where* to look; I still had to verify the fix actually worked locally.
- **Test scenario brainstorming** — when I was listing edge cases (empty results, missing resources, validation failures), I used AI to make sure I wasn't missing an obvious one. I then wrote and ran the tests myself.
- **Documentation polish** — tightening up the README structure and wording after I'd already decided what needed documenting.

So the honest split: AI accelerated *how fast I got unstuck or organized my thinking*; it didn't decide *what* the project does or *how* it's built.

---

## 2. What I validated, tested, or changed

I didn't take anything AI gave me at face value — everything got run through the same checklist:

- **Rewrote generated snippets to match my own coding style** rather than pasting them in as-is, so the codebase reads consistently.
- **Swapped generic exceptions for project-specific ones** (`ResourceNotFoundException`, `DuplicateResourceFoundException`) so error handling stays consistent with the rest of the app.
- **Rebuilt response handling** around my own global response wrapper instead of whatever shape AI defaulted to.
- **Simplified logic** in a few places where the suggested approach was more convoluted than it needed to be.

After any non-trivial change, I re-ran the full test suite, hit the endpoints manually in Postman, and rebuilt the Docker image to confirm nothing broke. If it didn't compile, run, and behave correctly, it didn't go in.

---

## 3. What I intentionally didn't use

**Swagger / OpenAPI** — AI suggested adding it, but I skipped it on purpose. The brief only asked for one optional feature, and I'd already covered that with Search, Monthly Summary, and Docker support. Adding Swagger on top would've been scope creep for no real payoff in this assessment.

**Extra libraries/frameworks** — a few suggestions pulled in dependencies I didn't need. If the Java standard library or Spring Boot already covered it, I kept it lightweight and skipped the extra dependency.

**Over-engineered abstractions** — some generated code introduced helper classes and layers of indirection that made things harder to follow, not easier. I flattened those back down to keep the code readable.

---

## How I actually worked

1. Figure out what the feature/problem actually needs.
2. Talk it through with AI if I want a second opinion or want to see alternatives I hadn't considered.
3. Pick the approach that fits *this* project, not just the "cleanest" generic answer.
4. Write/adapt the code myself.
5. Compile, run, test.
6. Fix what breaks.
7. Move on.

---

## Reflection

AI was most useful as a thinking partner — for weighing architectural options, catching edge cases I might've glossed over, and speeding up debugging and docs. It was least useful (and I ignored it) whenever it tried to over-engineer something simple or add scope I didn't need. The project you're looking at reflects decisions I made and verified myself, not something I generated and shipped unread.
