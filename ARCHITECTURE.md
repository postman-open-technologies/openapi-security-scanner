## OpenAPI Security Scanner Architecture

<!--
  The ADR record links in this document are auto-generated.
  After adding or removing an ADR, be sure to run `npm run decisions:build`
  to update this list.
-->

We need a basic security API to help API producers address the most common
challenges with securing their APIs. Taking an existing open source solution and
wrapping it as an API that will run in common cloud infrastructure, allowing
anyone to fork and deploy to support their own operations, while allowing us to
use to secure APIs as part of workshops, demos, and storytelling.

## Criteria

- It must be easy to demonstrate.
- It must run on self-managed infrastructure.
- It should be useful enough to adopt.

## Architecture Decisions

<!-- adrlog -->

* [ADR-0000](decisions/0000-use-markdown-architectural-decision-records.md) - Use Markdown Architectural Decision Records
* [ADR-0001](decisions/0001-owasp-zap-interaction.md) - Communicating with OWASP ZAP as the Scanning Engine

<!-- adrlogstop -->






