# OpenAPI Security Scanner
We need a basic security API to help API producers address the most common challenges with securing their APIs. Taking an existing open source solution and wrapping it as an API that will run in common cloud infrastructure, allowing anyone to fork and deploy to support their own operations, while allowing us to use to secure APIs as part of workshops, demos, and storytelling.

Proposed Tooling:

- OWASP Zap - https://www.zaproxy.org/docs/desktop/addons/openapi-support/
- Swurg / BurpSuite - https://github.com/PortSwigger/openapi-parser

Requirements:

- Leverage OpenAPI 3.1 as source for security scanning
- Run as simple one call API without authentication
- Follow Postman Open Technologies platform governance
