package mk.ukim.finki.emt.web;

import mk.ukim.finki.emt.dto.CreateAuthorDto;
import mk.ukim.finki.emt.dto.UpdateAuthorDto;
import mk.ukim.finki.emt.model.domain.Author;
import mk.ukim.finki.emt.model.projections.AuthorName;
import mk.ukim.finki.emt.service.application.AuthorApplicationService;
import mk.ukim.finki.emt.service.domain.AuthorsPerCountryViewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorApplicationService authorService;
    private final AuthorsPerCountryViewService authorsPerCountryViewService;
    public AuthorController(AuthorApplicationService authorService, AuthorsPerCountryViewService authorsPerCountryViewService) {
        this.authorService = authorService;
        this.authorsPerCountryViewService = authorsPerCountryViewService;
    }

    @GetMapping()
    public List<UpdateAuthorDto> getAllAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/by-country")
    public ResponseEntity<Integer> authors_per_country(Long countryId) {
        return authorsPerCountryViewService.authorsPerCountry(countryId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/names")
    public List<AuthorName> getAuthorNames() {
        return authorService.findAllProjectedBy();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UpdateAuthorDto> author_by_id(@PathVariable Long id) {
        return authorService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<UpdateAuthorDto> deleteAuthor(@PathVariable Long id) {
        return authorService.delete(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<UpdateAuthorDto> addAuthor(@RequestBody CreateAuthorDto author) {
        return authorService.save(author).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<UpdateAuthorDto> addAuthor(@PathVariable Long id, @RequestBody CreateAuthorDto author) {
        return authorService.update(id, author).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
