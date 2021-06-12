package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher tata = new Publisher("Mc Grahill","17440 N Tatum","Phoenix","AZ",85032);
        Publisher reliance = new Publisher("Tony Stark","1008 E Hives","New York","NY",1008);
        publisherRepository.save(tata);
        publisherRepository.save(reliance);
        System.out.println("Publisher in demand: "+publisherRepository.count());
        Author eric = new Author("Eric","Evans");
        Book hp =  new Book("Harry Potter","121645");
        eric.getBooks().add(hp);
        hp.getAuthors().add(eric);
        authorRepository.save(eric);
        bookRepository.save(hp);

        hp.setPublisher(tata);
        tata.getBooks().add(hp);
        publisherRepository.save(tata);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "434343656");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        authorRepository.save(rod);
        bookRepository.save(noEJB);

        noEJB.setPublisher(reliance);
        reliance.getBooks().add(noEJB);
        publisherRepository.save(reliance);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: "+ bookRepository.count());


        System.out.println("No. of books published by Tata: "+tata.getBooks().size());
        System.out.println("No. of books published by Reliance: "+reliance.getBooks().size());

    }
}
