package guru.qa.niffler.jupiter;

import guru.qa.niffler.api.SpendApiClient;
import guru.qa.niffler.model.CategoryJson;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

public class CategoryExtension implements BeforeEachCallback, AfterTestExecutionCallback, ParameterResolver {
    public final static ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(CategoryExtension.class);
    private final SpendApiClient spendApiClient = new SpendApiClient();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), Category.class)
                .ifPresent(
                        anno -> {
                            CategoryJson category;
                            if (anno.title().isEmpty()) {
                                category = new CategoryJson(
                                        null,
                                        "Новая категория" + Math.random(),
                                        anno.username(),
                                        false
                                );
                            } else {
                                category = new CategoryJson(
                                        null,
                                        anno.title(),
                                        anno.username(),
                                        false
                                );
                            }

                            CategoryJson created = spendApiClient.addCategory(category);
                            if (anno.archived()) {
                                CategoryJson archivedCategory = new CategoryJson(
                                        created.id(),
                                        created.name(),
                                        created.username(),
                                        true
                                );
                                created = spendApiClient.updateCategory(archivedCategory);
                            }
                            context.getStore(NAMESPACE).put(context.getUniqueId(), created);
                        }
                );
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws
            ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(CategoryJson.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws
            ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get(
                extensionContext.getUniqueId(),
                CategoryJson.class);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        CategoryJson category =
                context.getStore(NAMESPACE).get(context.getUniqueId(),
                        CategoryJson.class);
        if (!category.archived()) {
            spendApiClient.updateCategory(new CategoryJson(
                    category.id(),
                    category.name(),
                    category.username(),
                    true));
        }
    }
}