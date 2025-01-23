# Components of applicationContext.xml

## Schema URLs

Used https:// instead of http:// for Spring XSD locations.
Removed version-specific schema URLs to ensure compatibility with the latest versions.

## Component Scan
context:component-scan ensures that @Repository, @Service, and @Component are automatically detected.

## Transaction Management
tx:annotation-driven enables @Transactional without requiring explicit transaction handling in code.

## Aspect-Oriented Programming (AOP)
Supports cross-cutting concerns (e.g., logging, security, transactions).
