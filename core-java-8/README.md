# Java 8
#### 1. Lambda Expressions
#### 2. Method references
#### 3. Default Methods
#### 4. Type Annotations
#### 5. Collections via `Pipelines and Streams`
#### 6. Date-Time Package via `Date and Time API`
#### 7. Nashorn JavaScript Engine
#### 8. Concurrent Accumulators
#### 9. Security
#### 10. PermGen Space Removed
#### 11.Internationalization
#### 12. IO and NIO
#### 13. `java.lang` and `java.util` Packages
#### 14. JavaFX
#### 15. JDBC
#### 16. Java XML - JAXP


### 4. Type Annotations
###### For Example:
###### `java.lang.FunctionalInterface`

### 5. Collections via `Pipelines and Streams`
###### 1. Classes in the new `java.util.stream` package provide a `Stream API` to support functional-style operations on streams of elements. The `Stream API` is integrated into the `Collections API`, which enables bulk operations on collections, such as `sequential` or `parallel` map-reduce transformations.
###### 2. Performance Improvement for `HashMaps` with Key Collisions

### 8. Concurrent Accumulators
###### 1. Classes and interfaces have been added to the `java.util.concurrent` package.
###### 2. Methods have been added to the `java.util.concurrent.ConcurrentHashMap` class to support aggregate operations based on the newly added streams facility and `lambda expressions`.
###### 3. Classes have been added to the `java.util.concurrent.atomic` package to support scalable updatable variables.
###### 4. Methods have been added to the `java.util.concurrent.ForkJoinPool` class to support a common pool.
###### 5. The `java.util.concurrent.locks.StampedLock` class has been added to provide a capability-based lock with three modes for controlling `read/write` access.


### 9. Security
###### 1. Client-side `TLS 1.2` enabled by default
###### 2. New variant of `AccessController.doPrivileged` that enables code to assert a subset of its privileges, without preventing the full traversal of the stack to check for other permissions
###### 3. Stronger algorithms for password-based encryption
###### 4. SSL/TLS Server Name Indication (SNI) Extension support in JSSE Server
###### 5. Support for `AEAD` algorithms: The SunJCE provider is enhanced to support `AES/GCM/NoPadding` cipher implementation as well as `GCM` algorithm parameters. And the SunJSSE provider is enhanced to support `AEAD` mode based cipher suites. See Oracle Providers Documentation, JEP 115.
###### 6. KeyStore enhancements, including the new Domain KeyStore type `java.security.DomainLoadStoreParameter`, and the new command option `-importpassword` for the keytool utility
###### 7. `SHA-224` Message Digests
###### 8. Enhanced Support for NSA Suite B Cryptography
###### 9. Better Support for High Entropy Random Number Generation
###### 10. New `java.security.cert.PKIXRevocationChecker` class for configuring revocation checking of X.509 certificates
###### 11. 64-bit PKCS11 for Windows
###### 12. New rcache Types in Kerberos 5 Replay Caching
###### 13. Support for Kerberos 5 Protocol Transition and Constrained Delegation
###### 14. Kerberos 5 weak encryption types disabled by default
###### 15. Unbound SASL for the GSS-API/Kerberos 5 mechanism
###### 16. SASL service for multiple host names
###### 17. JNI bridge to native JGSS on Mac OS X
###### 18. Support for stronger strength ephemeral DH keys in the SunJSSE provider
###### 19. Support for server-side cipher suites preference customization in JSSE

### 11. Internationalization
###### 1. Unicode Enhancements, including support for Unicode 6.2.0
###### 2. Adoption of Unicode CLDR Data and the `java.locale.providers` System Property
###### 3. New Calendar and Locale APIs
###### 4. Ability to Install a Custom Resource Bundle as an Extension

### 13. `java.lang` and `java.util` Packages
###### 1. Parallel Array Sorting
###### 2. Standard Encoding and Decoding Base64
###### 3. Unsigned Arithmetic Support
###### For Example:
###### `java.util.Optional`
###### `java.util.function.Consumer`
###### `java.util.function.Function`
###### `java.util.function.Predicate`
###### `java.util.function.Supplier`
