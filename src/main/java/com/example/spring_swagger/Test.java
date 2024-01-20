package com.example.spring_swagger;

import com.example.spring_swagger.controller.PersonController;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.core.util.PathUtils;
import io.swagger.v3.core.util.ReflectionUtils;
import io.swagger.v3.oas.annotations.callbacks.Callback;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.servers.Server;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.AbstractRequestService;
import org.springdoc.core.GenericParameterService;
import org.springdoc.core.GenericResponseService;
import org.springdoc.core.MethodAttributes;
import org.springdoc.core.OpenAPIService;
import org.springdoc.core.OperationService;
import org.springdoc.core.PropertyResolverUtils;
import org.springdoc.core.RequestBodyService;
import org.springdoc.core.ReturnTypeParser;
import org.springdoc.core.SecurityService;
import org.springdoc.core.SpringDocConfigProperties;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.customizers.RouterOperationCustomizer;
import org.springdoc.core.customizers.SpringDocCustomizers;
import org.springdoc.core.fn.RouterOperation;
import org.springdoc.core.providers.ObjectMapperProvider;
import org.springdoc.webmvc.core.RequestService;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static org.springdoc.core.Constants.DEFAULT_SERVER_DESCRIPTION;
import static org.springdoc.core.Constants.DEFAULT_TITLE;
import static org.springdoc.core.Constants.DEFAULT_VERSION;
import static org.springdoc.core.converters.SchemaPropertyDeprecatingConverter.isDeprecated;

public class Test {
    public static void main(String[] args) throws JsonProcessingException {
        CustomRequestMappingHandlerMapping2 mapping = new CustomRequestMappingHandlerMapping2();
//
        mapping.detectHandlerMethods2(PersonController.class);
//        SpringDocConfigProperties springDocConfigProperties = new SpringDocConfigProperties();
//        OperationService operationParser = new OperationService();
//        GenericResponseService responseBuilder = new GenericResponseService();
//        SpringDocProviders springDocProviders = new SpringDocProviders();
//        SpringDocCustomizers springDocCustomizers = new SpringDocCustomizers();
        SpringDocConfigProperties springDocConfigProperties = new SpringDocConfigProperties();
        CustomBeanFactory customBeanFactory = new CustomBeanFactory();
        PropertyResolverUtils propertyResolverUtils = new PropertyResolverUtils(customBeanFactory, new DelegatingMessageSource(), springDocConfigProperties);
        SecurityService securityService = new SecurityService(propertyResolverUtils);


        GenericParameterService genericParameterService = new GenericParameterService(propertyResolverUtils, Optional.empty(), Optional.empty(), new ObjectMapperProvider(springDocConfigProperties), Optional.empty());

        RequestBodyService requestBodyService = new RequestBodyService(genericParameterService);

        OperationService operationService = new OperationService(genericParameterService, requestBodyService, securityService, propertyResolverUtils);

        List<ReturnTypeParser> returnTypeParsers = new ArrayList<>();
        returnTypeParsers.add(new ReturnTypeParser() {});

        GenericResponseService responseBuilder = new GenericResponseService(operationService, returnTypeParsers, springDocConfigProperties, propertyResolverUtils);

        OpenAPIService openAPIService = new OpenAPIService(Optional.empty(), securityService, springDocConfigProperties, propertyResolverUtils,
                Optional.empty(), Optional.empty(), Optional.empty());

        LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        RequestService requestService = new RequestService(genericParameterService, requestBodyService, operationService, Optional.empty(), localVariableTableParameterNameDiscoverer);
//        new CustomOpenApiResource(null,
//                ()->new OpenAPIService(Optional.empty(),securityService,springDocConfigProperties,propertyResolverUtils,
//                        Optional.empty(),Optional.empty(),Optional.empty()),
//                )
        SpringDocCustomizers springDocCustomizers = new SpringDocCustomizers(Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty());
        CustomOpenApiResource customOpenApiResource = new CustomOpenApiResource(operationService, requestService, responseBuilder, springDocCustomizers, openAPIService);
        OpenAPI openAPI = customOpenApiResource.start();
        Server server = new Server().url("http://localhost:8080").description(DEFAULT_SERVER_DESCRIPTION);
        List<Server> servers = new ArrayList<>();
        servers.add(server);
        openAPI.setServers(servers);
        System.out.println(writeJson(openAPI));
    }

    public static String writeJson(OpenAPI openAPI) throws JsonProcessingException {
        SpringDocConfigProperties springDocConfigProperties = new SpringDocConfigProperties();
        ObjectMapperProvider objectMapperProvider = new ObjectMapperProvider(springDocConfigProperties);

        return objectMapperProvider.jsonMapper().writerFor(OpenAPI.class).writeValueAsString(openAPI);
    }

    public static class CustomBeanFactory extends AbstractBeanFactoryWrapper{
        @Override
        public String resolveEmbeddedValue(String value) {
            return value;
        }
    }

    public static class AbstractBeanFactoryWrapper extends AbstractBeanFactory{
        @Override
        protected boolean containsBeanDefinition(String beanName) {
            return false;
        }

        @Override
        protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
            return null;
        }

        @Override
        protected Object createBean(String beanName, RootBeanDefinition mbd, Object[] args) throws BeanCreationException {
            return null;
        }

        @Override
        public <T> T getBean(Class<T> requiredType) throws BeansException {
            return null;
        }

        @Override
        public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
            return null;
        }

        @Override
        public <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType) {
            return null;
        }

        @Override
        public <T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType) {
            return null;
        }
    }

    public static class CustomOpenApiResource {

        private OperationService operationParser;
        private AbstractRequestService requestBuilder;
        private GenericResponseService responseBuilder;
        private SpringDocCustomizers springDocCustomizers;
        private OpenAPIService openAPIService;
//        protected SpringDocConfigProperties springDocConfigProperties;


        public CustomOpenApiResource(OperationService operationParser, AbstractRequestService requestBuilder, GenericResponseService responseBuilder, SpringDocCustomizers springDocCustomizers, OpenAPIService openAPIService) {
            this.operationParser = operationParser;
            this.requestBuilder = requestBuilder;
            this.responseBuilder = responseBuilder;
            this.springDocCustomizers = springDocCustomizers;
            this.openAPIService = openAPIService;
        }

        public OpenAPI buildOpenApi() {
            OpenAPI calculatedOpenAPI = new OpenAPI();
            calculatedOpenAPI.setComponents(new Components());
            calculatedOpenAPI.setPaths(new Paths());

            calculatedOpenAPI.setInfo(new Info().title(DEFAULT_TITLE).version(DEFAULT_VERSION));
            return calculatedOpenAPI;
        }

        protected OpenAPI start() {
            OpenAPI openAPI = buildOpenApi();
            CustomRequestMappingHandlerMapping2 mapping = new CustomRequestMappingHandlerMapping2();
            HashMap<RequestMappingInfo, HandlerMethod> methodTreeMap = mapping.detectHandlerMethods2(new PersonController());

            for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : methodTreeMap.entrySet()) {
                RequestMappingInfo requestMappingInfo = entry.getKey();
                HandlerMethod handlerMethod = entry.getValue();

                PatternsRequestCondition patternsRequestCondition = requestMappingInfo.getPatternsCondition();
                Set<String> patterns = patternsRequestCondition.getPatterns();

                if (!CollectionUtils.isEmpty(patterns)) {
                    Map<String, String> regexMap = new LinkedHashMap<>();
                    for (String pattern : patterns) {
                        String operationPath = PathUtils.parsePath(pattern, regexMap);
                        String[] produces = requestMappingInfo.getProducesCondition().getProducibleMediaTypes().stream().map(MimeType::toString).toArray(String[]::new);
                        String[] consumes = requestMappingInfo.getConsumesCondition().getConsumableMediaTypes().stream().map(MimeType::toString).toArray(String[]::new);
                        String[] headers = requestMappingInfo.getHeadersCondition().getExpressions().stream().map(Object::toString).toArray(String[]::new);
                        String[] params = requestMappingInfo.getParamsCondition().getExpressions().stream().map(Object::toString).toArray(String[]::new);
                        Set<RequestMethod> requestMethods = requestMappingInfo.getMethodsCondition().getMethods();
                        RouterOperation routerOperation = new RouterOperation(operationPath, requestMethods.toArray(new RequestMethod[requestMethods.size()]), consumes, produces, headers, params);

                        calculatePath2(handlerMethod,
                                routerOperation,
                                Locale.CHINA, openAPI);
                    }
                }
            }
            return openAPI;
        }

        public void calculatePath2(HandlerMethod handlerMethod,
                                   RouterOperation routerOperation, Locale locale, OpenAPI openAPI) {

//            routerOperation = customizeRouterOperation(routerOperation, handlerMethod);

            String operationPath = routerOperation.getPath();
            Set<RequestMethod> requestMethods = new TreeSet<>(Arrays.asList(routerOperation.getMethods()));
            io.swagger.v3.oas.annotations.Operation apiOperation = routerOperation.getOperation();
            String[] methodConsumes = routerOperation.getConsumes();
            String[] methodProduces = routerOperation.getProduces();
            String[] headers = routerOperation.getHeaders();
            Map<String, String> queryParams = routerOperation.getQueryParams();

            Components components = openAPI.getComponents();
            Paths paths = openAPI.getPaths();

//            Map<PathItem.HttpMethod, Operation> operationMap = null;
//            if (paths.containsKey(operationPath)) {
//                PathItem pathItem = paths.get(operationPath);
//                operationMap = pathItem.readOperationsMap();
//            }

//            JavadocProvider javadocProvider = operationParser.getJavadocProvider();

            for (RequestMethod requestMethod : requestMethods) {
//                Operation existingOperation = getExistingOperation(operationMap, requestMethod);
                Method method = handlerMethod.getMethod();
                // skip hidden operations
                if (operationParser.isHidden(method))
                    continue;

                RequestMapping reqMappingClass = AnnotatedElementUtils.findMergedAnnotation(handlerMethod.getBeanType(),
                        RequestMapping.class);

                MethodAttributes methodAttributes = new MethodAttributes(MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE, methodConsumes, methodProduces, headers, locale);
                methodAttributes.setMethodOverloaded(false);
                //Use the javadoc return if present
//                if (javadocProvider != null) {
//                    methodAttributes.setJavadocReturn(javadocProvider.getMethodJavadocReturn(handlerMethod.getMethod()));
//                }

                if (reqMappingClass != null) {
                    methodAttributes.setClassConsumes(reqMappingClass.consumes());
                    methodAttributes.setClassProduces(reqMappingClass.produces());
                }

                methodAttributes.calculateHeadersForClass(method.getDeclaringClass());
                methodAttributes.calculateConsumesProduces(method);

                Operation operation = new Operation();

                if (isDeprecated(method))
                    operation.setDeprecated(true);

                // Add documentation from operation annotation
                if (apiOperation == null || StringUtils.isBlank(apiOperation.operationId()))
                    apiOperation = AnnotatedElementUtils.findMergedAnnotation(method,
                            io.swagger.v3.oas.annotations.Operation.class);

                calculateJsonView(apiOperation, methodAttributes, method);
                if (apiOperation != null)
                    openAPI = operationParser.parse(apiOperation, operation, openAPI, methodAttributes);
                fillParametersList(operation, queryParams, methodAttributes);

                // compute tags
                operation = openAPIService.buildTags(handlerMethod, operation, openAPI, locale);

                io.swagger.v3.oas.annotations.parameters.RequestBody requestBodyDoc = AnnotatedElementUtils.findMergedAnnotation(method,
                        io.swagger.v3.oas.annotations.parameters.RequestBody.class);

                // RequestBody in Operation
                requestBuilder.getRequestBodyBuilder()
                        .buildRequestBodyFromDoc(requestBodyDoc, methodAttributes, components,
                                methodAttributes.getJsonViewAnnotationForRequestBody())
                        .ifPresent(operation::setRequestBody);
                // requests
                operation = requestBuilder.build(handlerMethod, requestMethod, operation, methodAttributes, openAPI);

                // responses
                ApiResponses apiResponses = responseBuilder.build(components, handlerMethod, operation, methodAttributes);
                operation.setResponses(apiResponses);

                // get javadoc method description
//                if (javadocProvider != null) {
//                    String description = javadocProvider.getMethodJavadocDescription(handlerMethod.getMethod());
//                    String summary = javadocProvider.getFirstSentence(description);
//                    boolean emptyOverrideDescription = StringUtils.isEmpty(operation.getDescription());
//                    boolean emptyOverrideSummary = StringUtils.isEmpty(operation.getSummary());
//                    if (!StringUtils.isEmpty(description) && emptyOverrideDescription) {
//                        operation.setDescription(description);
//                    }
//                    // if there is a previously set description
//                    // but no summary then it is intentional
//                    // we keep it as is
//                    if (!StringUtils.isEmpty(summary) && emptyOverrideSummary && emptyOverrideDescription) {
//                        operation.setSummary(javadocProvider.getFirstSentence(description));
//                    }
//                }

                Set<io.swagger.v3.oas.annotations.callbacks.Callback> apiCallbacks = AnnotatedElementUtils.findMergedRepeatableAnnotations(method, io.swagger.v3.oas.annotations.callbacks.Callback.class);

                // callbacks
                buildCallbacks(openAPI, methodAttributes, operation, apiCallbacks);

                // allow for customisation
                operation = customizeOperation(operation, handlerMethod);

                PathItem pathItemObject = buildPathItem(requestMethod, operation, operationPath, paths);
                paths.addPathItem(operationPath, pathItemObject);
            }
        }

//        public OpenAPI operationParserParse(io.swagger.v3.oas.annotations.Operation apiOperation,
//                                            Operation operation, OpenAPI openAPI, MethodAttributes methodAttributes){
//
//        }

        protected Operation customizeOperation(Operation operation, HandlerMethod handlerMethod) {
            Optional<List<OperationCustomizer>> optionalOperationCustomizers = springDocCustomizers.getOperationCustomizers();
            if (optionalOperationCustomizers.isPresent()) {
                List<OperationCustomizer> operationCustomizerList = optionalOperationCustomizers.get();
                for (OperationCustomizer operationCustomizer : operationCustomizerList)
                    operation = operationCustomizer.customize(operation, handlerMethod);
            }
            return operation;
        }

        protected RouterOperation customizeRouterOperation(RouterOperation routerOperation, HandlerMethod handlerMethod) {
            Optional<List<RouterOperationCustomizer>> optionalRouterOperationCustomizers = springDocCustomizers.getRouterOperationCustomizers();
            if (optionalRouterOperationCustomizers.isPresent()) {
                List<RouterOperationCustomizer> routerOperationCustomizerList = optionalRouterOperationCustomizers.get();
                for (RouterOperationCustomizer routerOperationCustomizer : routerOperationCustomizerList) {
                    routerOperation = routerOperationCustomizer.customize(routerOperation, handlerMethod);
                }
            }
            return routerOperation;
        }

        private void buildCallbacks(OpenAPI openAPI, MethodAttributes methodAttributes, Operation operation, Set<Callback> apiCallbacks) {
            if (!CollectionUtils.isEmpty(apiCallbacks))
                operationParser.buildCallbacks(apiCallbacks, openAPI, methodAttributes)
                        .ifPresent(operation::setCallbacks);
        }

        private PathItem buildPathItem(RequestMethod requestMethod, Operation operation, String operationPath,
                                       Paths paths) {
            PathItem pathItemObject;
            if (operation != null && !CollectionUtils.isEmpty(operation.getParameters())) {
                Iterator<Parameter> paramIt = operation.getParameters().iterator();
                while (paramIt.hasNext()) {
                    Parameter parameter = paramIt.next();
                    if (ParameterIn.PATH.toString().equals(parameter.getIn())) {
                        // check it's present in the path
                        String name = parameter.getName();
                        if (!StringUtils.containsAny(operationPath, "{" + name + "}", "{*" + name + "}"))
                            paramIt.remove();
                    }
                }
            }
            if (paths.containsKey(operationPath))
                pathItemObject = paths.get(operationPath);
            else
                pathItemObject = new PathItem();

            switch (requestMethod) {
                case POST:
                    pathItemObject.post(operation);
                    break;
                case GET:
                    pathItemObject.get(operation);
                    break;
                case DELETE:
                    pathItemObject.delete(operation);
                    break;
                case PUT:
                    pathItemObject.put(operation);
                    break;
                case PATCH:
                    pathItemObject.patch(operation);
                    break;
                case TRACE:
                    pathItemObject.trace(operation);
                    break;
                case HEAD:
                    pathItemObject.head(operation);
                    break;
                case OPTIONS:
                    pathItemObject.options(operation);
                    break;
                default:
                    // Do nothing here
                    break;
            }
            return pathItemObject;
        }

        private void fillParametersList(Operation operation, Map<String, String> queryParams, MethodAttributes methodAttributes) {
            List<Parameter> parametersList = operation.getParameters();
            if (parametersList == null)
                parametersList = new ArrayList<>();
            Collection<Parameter> headersMap = AbstractRequestService.getHeaders(methodAttributes, new LinkedHashMap<>());
            headersMap.forEach(parameter -> {
                Optional<Parameter> existingParam;
                if (!CollectionUtils.isEmpty(operation.getParameters())) {
                    existingParam = operation.getParameters().stream().filter(p -> parameter.getName().equals(p.getName())).findAny();
                    if (!existingParam.isPresent())
                        operation.addParametersItem(parameter);
                }
            });

            if (!CollectionUtils.isEmpty(queryParams)) {
                for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                    io.swagger.v3.oas.models.parameters.Parameter parameter = new io.swagger.v3.oas.models.parameters.Parameter();
                    parameter.setName(entry.getKey());
                    parameter.setSchema(new StringSchema()._default(entry.getValue()));
                    parameter.setRequired(true);
                    parameter.setIn(ParameterIn.QUERY.toString());
                    GenericParameterService.mergeParameter(parametersList, parameter);
                }
                operation.setParameters(parametersList);
            }
        }

        private void calculateJsonView(io.swagger.v3.oas.annotations.Operation apiOperation,
                                       MethodAttributes methodAttributes, Method method) {
            JsonView jsonViewAnnotation;
            JsonView jsonViewAnnotationForRequestBody;
            if (apiOperation != null && apiOperation.ignoreJsonView()) {
                jsonViewAnnotation = null;
                jsonViewAnnotationForRequestBody = null;
            } else {
                jsonViewAnnotation = AnnotatedElementUtils.findMergedAnnotation(method, JsonView.class);
                /*
                 * If one and only one exists, use the @JsonView annotation from the method
                 * parameter annotated with @RequestBody. Otherwise fall back to the @JsonView
                 * annotation for the method itself.
                 */
                jsonViewAnnotationForRequestBody = (JsonView) Arrays.stream(ReflectionUtils.getParameterAnnotations(method))
                        .filter(arr -> Arrays.stream(arr)
                                .anyMatch(annotation -> (annotation.annotationType()
                                        .equals(io.swagger.v3.oas.annotations.parameters.RequestBody.class) || annotation.annotationType().equals(RequestBody.class))))
                        .flatMap(Arrays::stream).filter(annotation -> annotation.annotationType().equals(JsonView.class))
                        .reduce((a, b) -> null).orElse(jsonViewAnnotation);
            }
            methodAttributes.setJsonViewAnnotation(jsonViewAnnotation);
            methodAttributes.setJsonViewAnnotationForRequestBody(jsonViewAnnotationForRequestBody);
        }

        private Operation getExistingOperation(Map<PathItem.HttpMethod, Operation> operationMap, RequestMethod requestMethod) {
            Operation existingOperation = null;
            if (!CollectionUtils.isEmpty(operationMap)) {
                // Get existing operation definition
                switch (requestMethod) {
                    case GET:
                        existingOperation = operationMap.get(PathItem.HttpMethod.GET);
                        break;
                    case POST:
                        existingOperation = operationMap.get(PathItem.HttpMethod.POST);
                        break;
                    case PUT:
                        existingOperation = operationMap.get(PathItem.HttpMethod.PUT);
                        break;
                    case DELETE:
                        existingOperation = operationMap.get(PathItem.HttpMethod.DELETE);
                        break;
                    case PATCH:
                        existingOperation = operationMap.get(PathItem.HttpMethod.PATCH);
                        break;
                    case HEAD:
                        existingOperation = operationMap.get(PathItem.HttpMethod.HEAD);
                        break;
                    case OPTIONS:
                        existingOperation = operationMap.get(PathItem.HttpMethod.OPTIONS);
                        break;
                    default:
                        // Do nothing here
                        break;
                }
            }
            return existingOperation;
        }
    }

    public static class CustomRequestMappingHandlerMapping2 extends RequestMappingHandlerMapping {

        protected HashMap<RequestMappingInfo, HandlerMethod> detectHandlerMethods2(Object handler) {
            Class handlerType = handler.getClass();
            Class<?> userType = ClassUtils.getUserClass(handlerType);
            HashMap<RequestMappingInfo, HandlerMethod> methodTreeMap = new HashMap<>();

            Map<Method, RequestMappingInfo> methods = MethodIntrospector.selectMethods(userType,
                    (MethodIntrospector.MetadataLookup<RequestMappingInfo>) method -> {
                        try {
                            return getMappingForMethod(method, userType);
                        } catch (Throwable ex) {
                            throw new IllegalStateException("Invalid mapping on handler class [" +
                                    userType.getName() + "]: " + method, ex);
                        }
                    });

            methods.forEach((method, mapping) -> {
                Method invocableMethod = AopUtils.selectInvocableMethod(method, userType);
                HandlerMethod handlerMethod = createHandlerMethod(handler, method);
                methodTreeMap.put(mapping,handlerMethod);
//                registerHandlerMethod(handler, invocableMethod, mapping);
            });

            return methodTreeMap;
        }
    }
}
