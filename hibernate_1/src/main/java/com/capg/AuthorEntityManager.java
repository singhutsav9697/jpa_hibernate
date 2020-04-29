package com.capg;


import javax.persistence.*;
import java.util.List;

public class ProjectMain {
    EntityManagerFactory emf;

    public static void main(String[] args) {
        ProjectMain project = new ProjectMain();
        project.execute();
    }

    void execute() {
        //entity manager factory created
        emf = Persistence.createEntityManagerFactory("author mgt");
        Author author = createAuthor();
        int authorId = author.getAuthorId();
        Author found = findAuthorById(authorId);
        print(found);

        found.setFirstName("Utsav");
        found.setMiddleName("Kumar");
        found.setLastName("singh");
        updateAuthor(found);

        print(found);

        List<Author>auth=findAllAuthor();

        System.out.println("Author");
        print(auth);
        emf.close();
    }

    void print(Author author){
        System.out.println("author id="+author.getAuthorId()+"First Name="+author.getFirstName()+"Middle Name="+author.getMiddleName()+"Last Name ="+author.getLastName()+"Phone No ="+author.getPhoneNo());

    }
    void print(List<Author>author){
        for (Author authors:author){
            print(authors);
        }
    }

    Author findAuthorById(int authorId) {
        EntityManager entityManager= emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Author a = entityManager.find(Author.class, authorId);
        transaction.commit();
        entityManager.close();
        return a;
    }

    List<Author> findAllAuthor(){
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query =entityManager.createQuery("from Author");
        List<Author> author=query.getResultList();
        return author;
    }

    Author createAuthor() {
        
        
        EntityManager entityManager= emf.createEntityManager();
        
      
        
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Author author = new Author();
        author.setFirstName("Utsav");
        author.setMiddleName("Kumar");
        author.setLastName("singh");
        author.setPhoneNo("9801427277");
        
        
        em.persist(author);   
        
        transaction.commit();
        
        System.out.println( author.getAuthorId());
        
        entityManager.close();
        return author;
    }
    
    Author updateAuthor(Author author){
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        author=entityManager.merge(author);
        transaction.commit();
        entityManager.close();
        return author;
    }

   
    void removeAuthorById(int id){
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Author author=entityManager.find(Author.class,id);
        entityManager.remove(author);
        transaction.commit();
        entityManager.close();
        System.out.println("author removed with id="+id);
    }
}