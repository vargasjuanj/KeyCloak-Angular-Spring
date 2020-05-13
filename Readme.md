## Profesor: Alberto Cortez, Metodología de Sistemas, UTN-FRM

# Inicio

* Comenzar con la [traducción](https://drive.google.com/file/d/1AhMXfzudg55LcnimpdAS2cWBEhW4d8xI/view) de [Sole](https://github.com/SoledadChinigioli)

* En el Frontend se usó el [ABMC](https://github.com/Florsalcedowd/abmGenerico) de Flor.

* El código de este proyecto es una mezcla de varias tutoriales y otras modificaciones.

# Asegurar un Reino, Territorio o Realm

![reino](https://user-images.githubusercontent.com/48636134/80867770-3f06fa80-8c6c-11ea-9fbb-380073cefbf0.png)

https://www.youtube.com/watch?v=RGp4HUKikts

## ¿Qué es Keycloak?

Es un servidor de seguridad que permite externalizar y delegar todos los aspectos de autenticación y autorización.

Es de código abierto, flexible y agnóstico de cualquier tecnología.

Además, Keycloak es más que un simple servidor de autenticación.

También proporciona un sistema completo de gestión de identidad, federación de usuarios para terceros como LDAP y mucho más


## Descargar e instalar

Descargar la distribución independiente del servidor [KeyCloak](https://downloads.jboss.org/keycloak/9.0.3/keycloak-9.0.3.zip) para Windows 

Ir a la carpeta bin y ejecutar standalone.bat

Tambien viene la imagen del contenedor para trabajar con docker y demás. [Consultar](https://www.keycloak.org/downloads.html) 


## Crear un Reino

Una vez iniciado el servidor de forma local abra un navegador y vaya a http://localhost:8080

Como es la primera vez que se ejecuta el servidor, tendrá que crear un usuario administrador.

Así que crearemos un usuario administrador, en este caso el nombre del usuario será admin, con contraseña admin.

![inicio](https://user-images.githubusercontent.com/48636134/80868471-31a03f00-8c71-11ea-8cd1-8a5c2e4122c8.png)


Keycloak define el concepto de un reino en el que definirá a sus clientes, lo que en la terminología de Keycloak significa una aplicación que estará protegida por Keycloak.
Puede ser una aplicación web, un backend Java EE, un Spring Boot, etc.

Una vez dentro de la consola de administración, creemos un nuevo reino simplemente haciendo clic en el botón "Add realm". Se llamará E-Commerce

![crear](https://user-images.githubusercontent.com/48636134/80868666-81333a80-8c72-11ea-89f9-94455d9eea8c.png)

![2](https://user-images.githubusercontent.com/48636134/80868723-c2c3e580-8c72-11ea-84ab-ea4acea882b0.png)

También está la posibilidad de importar un reino ya configurado.

Es posible configurar opciones como el registro de un cliente, verificación de email, tiempo de los tokens, temas visuales y otras configuraciones generales de seguridad.



## Crear clientes, roles y usuarios

### clientes
Ahora necesitamos definir un cliente, que será nuestra aplicación principal, nuestro cliente publico, el Frontend.

 Vaya a la sección "clients" y haga clic en el botón "create". Llamaremos a nuestro cliente "ecom-app":
 
 ![cliente](https://user-images.githubusercontent.com/48636134/80869860-189b8c00-8c79-11ea-9014-a8fed66acd9a.png)
 
En la siguiente pantalla, podemos mantener la configuración predeterminada, pero solo necesitamos ingresar una URL de redireccionamiento válida que Keycloak usará una vez que el usuario esté autenticado. 
Poner como valor: "*" , en "Web origin" igual ponemos "*", le damos a "save".
 
![frontend](https://user-images.githubusercontent.com/48636134/80893836-451ccb80-8cac-11ea-83de-993afce0d1b9.png)

 
Keycloak hace uso del protocolo  [openid-connect](https://inza.wordpress.com/2014/10/17/en-que-se-diferencian-saml-openid-y-oauth/) por defecto.

Utiliza [SSO](https://medium.com/@just_insane/keycloak-sso-part-1-what-is-single-sign-on-7229743c289b), inicio de sesión único, es un proceso de autenticación de usuario que ahorra tiempo y es altamente seguro.
 
SSO permite a los usuarios acceder a múltiples aplicaciones con una sola cuenta y cerrar sesión en todas las aplicaiones instantáneamente con un solo clic. Aplicaciones pertenecientes a un reino.
 

 Ahora vamos a crear dos clientes más, uno de ellos será un servidor de descanso, el cual cumple la función de ser consultado para obtener data.
 
 El primero se llamará products y el servicio de descanso suppliers. Los dos tendrán un tipo de acceso "bearer-only"
 
 
Los clientes 'confidential' requieren un secreto para iniciar el protocolo de inicio de sesión. 
Los clientes 'public' no requieren un secreto. Los clientes 'bearer-only'(solo portadores) son servicios web que nunca inician un inicio de sesión.


![service](https://user-images.githubusercontent.com/48636134/80870229-834dc700-8c7b-11ea-9faa-1f3a19e39d1c.png)
### roles

Ahora, definiremos dos roles que se asignarán a nuestros usuarios, creemos dos roles simples, uno llamado "admin" y el otro user.


![admin1](https://user-images.githubusercontent.com/48636134/80870637-1b4cb000-8c7e-11ea-9bc3-1e4b9e599e5b.png)

![roles](https://user-images.githubusercontent.com/48636134/80870677-6c5ca400-8c7e-11ea-93b1-465f44ba47ce.png)

### usuarios

Y por último, pero no menos importante, creemos dos usuarios, solo se necesita la propiedad de nombre de usuario, aunque se podrian crear más.
Tratar de completar todos los datos del usuario, no como en la siguiente imagen.

![user1](https://user-images.githubusercontent.com/48636134/80870749-e9881900-8c7e-11ea-9a65-f824ddc8f0c4.png)

Una vez creado nos vamos a la sección "Role Mapping" de este usuario y le asignamos el rol "admin". Al otro usuario solo le asignamos el rol de "user".

Y finalmente, necesitamos establecer sus credenciales, así que vaya a la pestaña de credenciales de sus usuario y elija una contraseña,
Asegúrese de desactivar la bandera "Temporal" a menos que usted desea que el usuario tenga que cambiar su contraseña la primera vez que se autentica.

![credentials](https://user-images.githubusercontent.com/48636134/80870888-9febfe00-8c7f-11ea-881c-3f54b4bf94a4.png)

## Arquitectura

* Cuando desde el navegador se accede al frontend y el usuario se loguea. La aplicación Angular se comunica con el servidor Keycloak para validar el usuario. Si está todo correcto Keycloak construye un token y el usuario puede acceder a ciertos servicios según el token.

* Una vez logueado, si el usuario por ejemplo tiene el rol de "admin" y desea acceder a la ruta "/suppliers".
 Primero desde Angular se coloca el token en la petición, el servidor products lo analiza y lo autoriza, luego hace una petición al servidor suppliers pasandole el token tambien; este servicio lo valida  y le devuelve la lista de productos al servicio products y products a la app de Angular.
## Flujo
![1](https://user-images.githubusercontent.com/48636134/80891200-f0bc2080-8c98-11ea-9e4f-9af7a1183330.png)

![2](https://user-images.githubusercontent.com/48636134/80891207-fd407900-8c98-11ea-9424-ed5719aa748c.png)

* El código brindado en esta [lista](https://www.youtube.com/playlist?list=PL5hlqK5UE6o35CQcHY3PbEbD-JFCtJGFM) no está completo, se trató de rescatar lo mejor.


* [Sobre JWT](https://www.youtube.com/watch?v=4I6boKqiRy8&list=PLj2IVmcP-_QNCNAfCQ3gzoX6ytsxeoKZB&index=11)



## Keycloak y Angular

* [Instalar la dependencia](https://www.npmjs.com/package/keycloak-angular) de keycloak "npm i --save keycloak-js",  y agregar al angular.json  "node_modules/keycloak-js/dist/keycloak.min.js"

* En el archivo app.module se debe configurar el módulo Keycloak para que inicie y asegure a la aplicación frontend antes de iniciar completamente.
 
* Crear un servicio para menejar Keycloak y configurar a que realm pertenece, a que cliente pertenece, etc. 
Se hace uso del [adaptador para javascript](https://www.keycloak.org/docs/latest/securing_apps)

* Se debe crear un servicio que implemente la interfaz [HttpInterceptor](https://wwomw.youtube.com/playlist?list=PLCKuOXG0bPi1ckqpCC2Aju2gBANzc6MTv). 
Para que a cada petición se agregue en su cabecera el token que permite el acceso a los distintos microservicios o partes de la aplicación Angular.

* Configurar las [guards](https://codingpotions.com/angular-seguridad) para dar acceso a ciertas rutas, dependiendo si el usuario está autenticado o no, y dependiendo del rol.

* Se copió un ABMC para ejemplicar; y algunos elementos html estarán disponibles o no, dependiendo el nivel de autorización que posea el usuario.

## Spring Boot y Keycloak

Keycloak proporciona adaptadores para una aplicación que necesita interactuar con una instancia de Keycloak.
 
Hay adaptadores para WildFly / EAP, NodeJS, Javascript y, por supuesto, para Spring Boot.

* Incorporar el [adaptador](https://github.com/keycloak/keycloak-documentation/blob/master/securing_apps/topics/oidc/java/spring-boot-adapter.adoc) o [dependencias](https://mvnrepository.com/artifact/org.keycloak/keycloak-spring-boot-starter/9.0.0) de keycloak en el archivo pom:

```  
<dependency>
        <dependency>
            <groupId>org.keycloak</groupId>
            <artifactId>keycloak-spring-boot-starter</artifactId>
            <version>9.0.0</version>
        </dependency>
     </dependency>
		  
<dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.keycloak.bom</groupId>
                <artifactId>keycloak-adapter-bom</artifactId>
                <version>${keycloak.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

```

* Configurar el archivo application.properties con los datos del realm. Los [cors](https://www.youtube.com/watch?v=yBCFSOXZK44&list=UUekUbCGf-ZjLNF0Au-9y77A&index=68) se poenen en true para habilitar el dominio cruzado y evitar el bloqueo de las politicas de cors.

```  
server.port = 8081

# Keycloak properties
keycloak.realm = E-Commerce
keycloak.auth-server-url = http://localhost:8080/auth
keycloak.ssl-required = external
keycloak.resource = products
keycloak.bearer-only = true
#keycloak.principal-attribute=preferred_username
keycloak.principal-attribute= name
keycloak.cors = true

```  
. Servicio de descanso

```  
server.port = 8082

# Keycloak properties
keycloak.realm = E-Commerce
keycloak.auth-server-url = http://localhost:8080/auth
keycloak.ssl-required = external
keycloak.resource = suppliers
keycloak.bearer-only = true
#keycloak.principal-attribute=preferred_username
keycloak.principal-attribute= name
keycloak.cors = true

```  

* Con lo visto anteriormente ya está asegurado todo el reino y sus clientes.
 Pero es necesario utilizar en el backend las ventajas que ofrece [Spring Security](https://www.youtube.com/watch?v=zKOBeeqTKFo), como especificar los permisos de cada endpoint o controlador de acuerdo a los roles de cada usuario.
 
 . Dependencia:
 
```  
 <dependencia>
   <groupId> org.springframework.boot </groupId>
   <artifactId> spring-boot-starter-security </artifactId>
</dependency>

```  
* Al igual que cualquier otro proyecto que esté protegido con Spring Security, se necesita una clase de configuración que extienda WebSecurityConfigurerAdapter.
 Keycloak proporciona su propia subclase.
 
 ```  
 @KeycloakConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    /**
     * Registers the KeycloakAuthenticationProvider with the authentication manager.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        KeycloakAuthenticationProvider keyCloakAuthProvider = keycloakAuthenticationProvider();
        keyCloakAuthProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keyCloakAuthProvider);
    }

    @Bean
    public KeycloakConfigResolver KeyCloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    /**
     * Defines the session authentication strategy.
     */
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new NullAuthenticatedSessionStrategy();
    }

    @Bean
    public FilterRegistrationBean keycloakAuthenticationProcessingFilterRegistrationBean(
            KeycloakAuthenticationProcessingFilter filter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean keycloakPreAuthActionsFilterRegistrationBean(
            KeycloakPreAuthActionsFilter filter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
        registrationBean.setEnabled(false);
        return registrationBean;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.cors()
                .and()
                .csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .sessionAuthenticationStrategy(sessionAuthenticationStrategy())
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").hasAnyRole("admin", "user")
                .anyRequest().permitAll();
                //.anyRequest().authenticated()
                //.anyRequest().denyAll()
 
    }
    @Bean
    KeycloakRestTemplate keycloakRestTemplate(KeycloakClientRequestFactory keycloakClientRequestFactory) {
        return new KeycloakRestTemplate(keycloakClientRequestFactory);

    }
}

 ```

* Echemos un vistazo más de cerca a los métodos y anotaciones más importantes ( [Más info sobre adaptador para  spring boot en la doc ](https://www.keycloak.org/docs/latest/securing_apps/)): 

. configureGlobal: Aquí cambiamos el Granted Authority Mapper, de manera predeterminada en Spring Security, los roles tienen el prefijo ROLE_,  podríamos cambiar eso en nuestra configuración de Reino, pero podría ser confuso para otras aplicaciones que no conocen esta convención, así que aquí asignamos un SimpleAuthorityMapper que se asegurará de que no se agregue ningún prefijo.

. keycloakConfigResolver: por defecto, el adaptador de seguridad de Spring Keycloak buscará un archivo llamado keycloak.json presente en su classpath. Pero aquí queremos aprovechar el soporte del archivo de propiedades Spring Boot.

. configure: Aquí es donde definimos nuestras restricciones de seguridad, bastante simple de entender, aseguramos la ruta "/api" y sus hijas con el rol admin y user. 
 
. Debe proporcionar un bean de estrategia de autenticación de sesión que debe ser del tipo RegisterSessionAuthenticationStrategy para aplicaciones públicas o confidenciales y NullAuthenticatedSessionStrategy para aplicaciones de solo portador.

. La anotación @KeycloakConfiguration es una anotación de metadatos que define todas las anotaciones necesarias para integrar Keycloak en Spring Security. Si tiene una configuración compleja de Spring Security, simplemente puede echar un vistazo a las anotaciones de la anotación @Keycloak Configuration y crear su propia meta anotación personalizada o simplemente usar anotaciones Spring específicas para el adaptador Keycloak.

. KeycloakRestTemplate funciona casi igual que la [restTemplate](https://spring.io/guides/gs/consuming-rest/), a diferencia que pasa el token de un servicio a otro. En este caso se coloca en products la cual consume suppliers. [Otro ejemplo de restTemplate](https://github.com/vargasjuanj/tp3lab4)
## Probar


* Iniciar KeyCloak

* Importar el realm de la carpeta docs

* Crear por lo menos dos usuarios con sus credenciales. Asignarle a uno el rol "admin".

* Se recomienda primero importar los clientes que usan maven con IntelliJ o STS para cargar bien las dependencias. 
Después si se quieren iniciar todos los clientes se recomienda con VSCODE para equipos de bajo-medio recursos, habiendo instalado el package de java correspondiente.
(También se podria optar por un navegador más liviano como Vivaldi)

* Iniciar ecom-app y los dos servicios.

* Ir a http://localhost:4200 y probar



![image](https://user-images.githubusercontent.com/48636134/80907532-669ea700-8cee-11ea-942a-4b2295ff661a.png)




![image](https://user-images.githubusercontent.com/48636134/80907544-7cac6780-8cee-11ea-9f14-c9cfec77d16f.png)



![image](https://user-images.githubusercontent.com/48636134/80907562-a2397100-8cee-11ea-8800-72b9ff740496.png)




![image](https://user-images.githubusercontent.com/48636134/80907594-c432f380-8cee-11ea-84f4-b8e251694b53.png)




![image](https://user-images.githubusercontent.com/48636134/80907639-02c8ae00-8cef-11ea-9518-8978284e9753.png)




## Otros temas pendientes para ver o investigar

* Themes personalizados

* Deploy

* Conexión a base de datos, recuperar datos de usuarios (Se pueden recuperar algunos datos desde el front, pero hay que ver desde el backend)

* [Integración con Spring Web Flux](https://www.youtube.com/watch?v=07L-Kxq0VV0)

* https://www.youtube.com/watch?v=0cziL__0-K8

* https://www.youtube.com/watch?v=j3uydtrYLSE

* [Login con redes sociales, y demás](http://www.mastertheboss.com/jboss-frameworks/keycloak/google-social-login-with-keycloak)

* Rutas padres e hijas con guards.





