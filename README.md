# customer-service

Customer Service Project

customer service: http://3.15.40.28:8080/

## API List
| API	| Type	| Url					| Controller|
| ----	| ---	| --					| ----------|
| Get solicitation Name by customer Id | POST| /api/v1/solicitation/list| SolicitationPackageController|
| Get Solicitation Name by customer Id | POST| /api/v1/solicitation/list| SolicitationPackageController|
| Get Customer| POST| /api/v1/customersWithoutAuth| CustomerController|
| Customer user details| POST| /api/v1/customerUserDetails| CustomerController|
| Step 1.b: Get list of packages with solication id and customer id (if step 1 says true and has solicitation id)| POST| /api/v1/solicitation| SolicitationPackageController|
| Get list of benefits for the enrolled package| POST| /core/memberships/v2/membership/benefits/389| MembershipController|

## Steps to Run an application
1. Take the latest copy of the code from the repository
2. Open project.
3. build the project using Gradle-><<Project>>->Tasks->build->build.
4. Once Project get build successfully, press Shift+F10 to run it.