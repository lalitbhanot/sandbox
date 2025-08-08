football obs  project
Paths.get(folder) creates a path relative to your current working directory
When you run from IntelliJ, the working directory is D:\intellij\sandbox\footballobs\
So it was looking for D:\intellij\sandbox\footballobs\teams\ (file system path)
But you moved the folder to src/main/resources/teams/ which becomes part of the classpath, not the file system

File System Path (what your old code expected):
D:\intellij\sandbox\footballobs\teams\file.json

Classpath Resource (where your file actually is):
Inside the JAR: /teams/file.json (accessible via ClassPathResource)

public void loadFile() throws IOException {
// Step 1: Create a resolver that can find resources in the classpath
ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    try {
        // Step 2: Look for ALL files in the classpath folder using a pattern
        // "classpath:teams/*" means "find all files in the teams folder in classpath"
        Resource[] resources = resolver.getResources("classpath:" + folder + "/*");
        
        // Step 3: Check if any files were found
        if (resources.length == 0) {
            throw new IOException("No files found in classpath:" + folder);
        }
        
        // Step 4: Take the first file (similar to your original .findFirst())
        Resource resource = resources[0];
        loadFileFromResource(resource);
        
    } catch (Exception e) {
        throw new IOException("Error loading files from folder: " + folder, e);
    }
}

private void loadFileFromResource(Resource resource) throws Exception {
this.fileName = resource.getFilename();
ObjectMapper mapper = new ObjectMapper();

    // Step 5: Read from InputStream (not File object!)
    // This is crucial - classpath resources must be read as streams
    try (InputStream inputStream = resource.getInputStream()) {
        teams = mapper.readValue(inputStream, new TypeReference<List<String>>() {});
    }
}

---
Are @Autowired Annotation and Constructor Injection the Same Thing?
No, the @Autowired annotation and constructor injection are not the same thing, although they are related concepts in the context of dependency injection in Spring Framework.

🆚 Comparison of @Autowired and Constructor Injection
Feature	@Autowired Annotation	Constructor Injection
Definition	An annotation used to automatically inject dependencies.	A method of injecting dependencies through a class constructor.
Usage	Can be used on fields, methods, or constructors.	Specifically used in the constructor of a class.
Flexibility	Allows for optional dependencies if used with required = false.	Requires all dependencies to be provided at instantiation.
Readability	Can lead to less clear code if used extensively on fields.	Promotes immutability and clearer dependencies.
Testing	Can make unit testing more complex due to hidden dependencies.	Easier to test as dependencies are explicit.
🔍 Detailed Explanation
@Autowired Annotation: This annotation is part of Spring's dependency injection mechanism. It can be applied to fields, setter methods, or constructors. When applied, Spring automatically resolves and injects the required beans from the application context. For example:
java

Copy Code
@Autowired
private PlayerService playerService;
Constructor Injection: This is a specific form of dependency injection where dependencies are provided through a class constructor. It ensures that all required dependencies are available when the object is created. For example:
java

Copy Code
public class PlayerController {
private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
}
📝 Conclusion
While @Autowired can be used with constructor injection, they are not synonymous. Constructor injection is a specific technique for injecting dependencies, while @Autowired is an annotation that can facilitate various forms of dependency injection, including constructor injection. Using constructor injection is generally recommended for better code clarity and testability.
---
public model.com.lalit.football.Player getPlayer(String id) {
return players.values().stream()
.filter(player -> player.getId().equals(id))
.findFirst()
.orElseThrow(() -> new NotFoundException("model.com.lalit.football.Player not found"));
}

