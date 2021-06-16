# intuit-product-service
Product service used for actions from individual products

We will run one instance each for different product.
Spring profile will be different for each product's instance.

VM args :

Accounts product service -
java -Dlogsdir="<any_path>" -jar <base_path>\intuit-product-service\target\intuit-product-service-1.0.0-SNAPSHOT.jar --spring.profiles.active=accounts

Timesheet product service -
java -Dlogsdir="<any_path>" -jar <base_path>\intuit-product-service\target\intuit-product-service-1.0.0-SNAPSHOT.jar --spring.profiles.active=timesheet

Payroll product service -
java -Dlogsdir="<any_path>" -jar <base_path>\intuit-product-service\target\intuit-product-service-1.0.0-SNAPSHOT.jar --spring.profiles.active=payroll

Payment product service -
java -Dlogsdir="<any_path>" -jar <base_path>\intuit-product-service\target\intuit-product-service-1.0.0-SNAPSHOT.jar --spring.profiles.active=payment
