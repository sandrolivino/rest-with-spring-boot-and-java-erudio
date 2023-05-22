## _Seção 13 - Content Negotiation:_ 
### Definição: capacidade de servir diferentes formatos da mesma informação JSON, XML, PDF, DOC etc.
* Fonte: https://www.baeldung.com/spring-mvc-content-negotiation-json-xml

* Implementaremos apenas a nível de formato: JSON, XML e YML.

#### Histórico
Implementar Content Negotiation via QueryParam. Antes era possível fazer via EXTENSION, mas foi depreciado:
* ANTES: http://localhost:8080/api/person/v1.xml
* AGORA: http://localhost:8080/api/person/v1?mediaType=xml

Passo a passo:
### Adicionar nova dependência.
```
<dependency>
	<groupId>com.fasterxml.jackson.dataformat</groupId>
	<artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```

### Criar a classe WebMvcConfigurer em um novo pacote "config"
```
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Com essa annotation o spring saberá que precisa executar essa classe no carregamento
@Configuration
public class WebConfig implements WebMvcConfigurer {
}
```


### Sobrescrever o método configureContentNegotiation
* Botão direito sobre WebMvcConfigurer>> Generate: Override...
```
  @Configuration
  public class WebConfig implements WebMvcConfigurer {
  @Override
  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
  WebMvcConfigurer.super.configureContentNegotiation(configurer);
  }
  }
```

### Implementar as propriedades do método configureContentNegotiation
```
        configurer.favorParameter(true)
                .parameterName("mediaType")
                .ignoreAcceptHeader(true)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
```
* parameterName: valor passado na url
* defaultContentType: MediaType default caso nada seja passado
* mediaType: tipos de MediaTypes suportados. Informar todas que forem necessárias.

### Ajustar os controllers para produzirem também XML
* Até o momento somente produzíamos JSON
```

```

