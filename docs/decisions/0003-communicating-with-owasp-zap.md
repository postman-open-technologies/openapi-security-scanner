# Communicating with OWASP ZAP as the Scanning Engine

- Status: draft

## Context and Problem Statement

OWASP ZAP is a complex application for security testing HTTP servers. It's
comprised of a number of different executable clients (e.g., desktop, cli), an
API server, and a database. To leverage ZAP as the engine for scanning APIs, what
method of communication must be established?

## Considered Options

- OWASP ZAP automation driven by Docker.
- OWASP ZAP automation driven by a client library.

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

<!-- ### OWASP ZAP automation driven by a client library -->
<!-- ## Decision Outcome -->
