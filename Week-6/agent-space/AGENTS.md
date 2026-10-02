# AGENTS.md

This file is the single source of truth for AI coding agents working in this
repository. It is read automatically by GitHub Copilot, Claude Code, Cursor,
Cline, Codex, OpenHands, and other agent harnesses.

## Project overview
This repository stores a personal static web page for the user. It is a
single-page personal site — no frameworks, no build step, no dependencies.

## Stack (hard constraints)
- **Vanilla HTML, CSS, and JavaScript only.** No frameworks, libraries, or
  package managers. No build tooling.
- Deliverable is plain static files that open directly in a browser.

## Design system
- **Primary color:** silver.
- **Accent / highlight colors:** gold and red.
- Use silver as the dominant color; reserve gold and red for accents,
  highlights, and interactive states (links, buttons, hover, emphasis).

## Workflow rules
- Keep changes minimal and targeted; do not refactor unrelated code.
- Follow the existing style of the file you are editing.
- Test by opening the page in a browser; verify it renders without console
  errors.

## Do / Don't
- Do: keep everything self-contained in static HTML/CSS/JS files.
- Don't: introduce a framework, package manager, or build step.
- Don't: commit generated artifacts or secrets.
