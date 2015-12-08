# basicAuth

https://gist.github.com/przodownikR1/3d01c702c359896b3f4c


Generate keystore with self-signed certificate in it

keytool -genkey -alias keyAlias -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore myKeystore.p12 -validity 3650

przodownik ~/b/basicAuth (ssl|✚2)$ keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650  
Enter keystore password:  
Re-enter new password: 
They don't match. Try again
Enter keystore password:  
Re-enter new password: 
What is your first and last name?
  [Unknown]:  Borowiec
What is the name of your organizational unit?
  [Unknown]:  scalatech
What is the name of your organization?
  [Unknown]:  scalatech
What is the name of your City or Locality?
  [Unknown]:  Warsaw
What is the name of your State or Province?
  [Unknown]:  mazowieckie
What is the two-letter country code for this unit?
  [Unknown]:  PL
Is CN=Borowiec, OU=scalatech, O=scalatech, L=Warsaw, ST=mazowieckie, C=PL correct?
  [no]:  


Check keystore contents:

przodownik ~/ (ssl|)$ keytool -list -keystore keystore.p12 -storetype PKCS12
Enter keystore password:  

Keystore type: PKCS12
Keystore provider: SunJSSE

Your keystore contains 1 entry

tomcat, 2015-12-06, PrivateKeyEntry, 
Certificate fingerprint (SHA1): CB:48:14:E4:6A:A9:DF:F3:D2:84:90:0C:5B:DE:DE:32:19:FF:6A:32
przodownik ~/b/b/s/m/resources (ssl|)$ 