@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

    @EntityGraph(attributePaths = {"createdBy"})
    @Query("SELECT su FROM ShortUrl su WHERE su.isPrivate = false ORDER BY su.createdAt DESC")
    List<ShortUrl> findPublicUrls();
    
    // Multiple associations
    @EntityGraph(attributePaths = {"createdBy", "tags", "category"})
    List<ShortUrl> findByIsPrivateFalse();
    
    // Nested associations
    @EntityGraph(attributePaths = {"createdBy.profile", "createdBy.roles"})
    List<ShortUrl> findAll();
}

---
addFlashAttribute

Scope: Survives one redirect (flash scope)
Storage: Stored in session temporarily, removed after next request
Use case: Pass data through redirects (PRG pattern - Post-Redirect-Get)
Lifecycle: Available only for the next request after redirect
@PostMapping("/save")
public String saveUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
userService.save(user);
redirectAttributes.addFlashAttribute("message", "User saved successfully!");
redirectAttributes.addFlashAttribute("user", user);
return "redirect:/users"; // Data survives this redirect
}

@GetMapping("/users")
public String listUsers(Model model) {
// Flash attributes are automatically available in the model
// "message" and "user" will be accessible in the view
return "users";
}
addAttribute (Model/ModelMap)

Scope: Current request only
Storage: Request scope
Use case: Pass data to views in same request
Lifecycle: Dies when request completes

java@GetMapping("/users")
public String listUsers(Model model) {
model.addAttribute("users", userService.findAll());
model.addAttribute("message", "Welcome!");
return "users"; // Forward to view - data available
}

@PostMapping("/save")
public String saveUser(@ModelAttribute User user, Model model) {
userService.save(user);
model.addAttribute("message", "Saved!"); // Lost on redirect!
return "redirect:/users"; // Message will be lost
}
When to use what:
Use addFlashAttribute:

After form submissions that redirect
Displaying success/error messages after redirects
Passing data through the PRG pattern
Temporary data that should survive one redirect

Use addAttribute:

Displaying data on the same request
Populating forms
Showing lists/details without redirects
Any data that doesn't need to survive redirects

The main point: flash attributes survive redirects, regular model attributes don't.