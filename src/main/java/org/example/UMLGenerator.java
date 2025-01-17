package org.example;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

    public class UMLGenerator {
    private static final Set<String> components = new HashSet<>();
    private static final List<String> connections = new ArrayList<>();

    public static void main(String[] args) {
        String dirPath = "src/main/java/org/example"; // Directory containing Java files

        try (Stream<Path> paths = Files.walk(Paths.get(dirPath))) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .forEach(path -> processJavaFile(path.toFile()));

            // Generate and print the C3 model diagram
            generateC3Model();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processJavaFile(File file) {
        try (FileInputStream in = new FileInputStream(file)) {
            JavaParser parser = new JavaParser();
            CompilationUnit cu = parser.parse(in).getResult().orElseThrow(() -> new RuntimeException("Failed to parse " + file.getName()));

            cu.findAll(ClassOrInterfaceDeclaration.class).forEach(cls -> {
                String componentName = cls.getNameAsString();
                components.add(componentName);

                // Inheritance (extends) and Implements (interfaces) relationships
                cls.getExtendedTypes().forEach(ext -> {
                    connections.add(componentName + " --> " + ext.getNameAsString());
                });

                cls.getImplementedTypes().forEach(impl -> {
                    connections.add(componentName + " --> " + impl.getNameAsString());
                });

                // Field associations (dependencies)
                cls.getFields().forEach(field -> {
                    String fieldType = field.getElementType().toString();
                    if (components.contains(fieldType)) {
                        connections.add(componentName + " --> " + fieldType);
                    }
                });

                // Method parameters and return types (dependencies)
                cls.getMethods().forEach(method -> {
                    String returnType = method.getType().toString();
                    if (components.contains(returnType)) {
                        connections.add(componentName + " --> " + returnType);
                    }
                    method.getParameters().forEach(param -> {
                        String paramType = param.getType().toString();
                        if (components.contains(paramType)) {
                            connections.add(componentName + " --> " + paramType);
                        }
                    });
                });
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateC3Model() {
        System.out.println("@startuml");

        // Print components (in the context of a container)
        System.out.println("package Container {");
        components.forEach(component -> System.out.println("  component " + component));
        System.out.println("}");

        // Print connections
        connections.forEach(System.out::println);

        System.out.println("@enduml");
    }
}
