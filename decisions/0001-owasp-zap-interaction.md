# Communicating with OWASP ZAP as the Scanning Engine

- Status: proposed
- Deciders: Kevin Swiber <kevin.swiber@postman.com>, Meenakshi Dhanani
  <meenakshi.dhanani@postman.com>

## Context and Problem Statement

OWASP ZAP is a complex application for security testing HTTP servers. It's
comprised of a number of different executable clients (e.g., desktop, cli), an
API server, and a database. To leverage ZAP as the engine for scanning APIs, a
method of communication must be established.

## Considered Options

- OWASP ZAP automation driven by Docker.
- OWASP ZAP automation driven by a client library.

<!--
## Decision Outcome

Chosen option: "[option 1]", because [justification. e.g., only option, which
meets k.o. criterion decision driver | which resolves force force | … | comes
out best (see below)].
-->

<!--
### Positive Consequences

- [e.g., improvement of quality attribute satisfaction, follow-up decisions
  required, …]
- …

### Negative Consequences

- Debugging the ZAP daemon as an opaque box requires deep knowledge and
  experience with the technology.
-->

## Pros and Cons of the Options

### OWASP ZAP automation driven by Docker

OWASP ZAP has a Docker image with a
[container execution model](https://www.zaproxy.org/docs/docker/about/) for
starting the ZAP daemon and interacting with the API. There's also access to
headless ZAP and running options from the command line.

- Good, because ops concerns can be combined with simple scripts.
- Good, because management of ZAP releases is represented by an image reference
  update.
- Bad, because container orchestration adds another level of debugging.

<!--
### OWASP ZAP automation driven by a client library

[example | description | pointer to more information | …]

- Good, because [argument a]
- Good, because [argument b]
- Bad, because [argument c]
-->
