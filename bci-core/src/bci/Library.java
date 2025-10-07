package bci;

import bci.exceptions.*;
import bci.search.DefaultSearch;
import bci.search.SearchByCreator;
import bci.search.SearchByPrice;
import bci.works.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




=======
import java.util.List;
import bci.user.*;
//FIXME maybe import classes
>>>>>>> 90dee6fa8b40c8f729c236159352f290a4893f73

/** Class that represents the library as a whole. */
class Library implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

<<<<<<< HEAD
    
    private Map<String, Creator> creatorMap = new HashMap<>();
    private Map<Integer, Work> workMap = new HashMap<>();
    // Minimal user registry for USER:name:email lines (name -> email)
    private Map<String, String> userMap = new HashMap<>();
    // Auto-increment id for imported works (since text format has no id)
    private int nextWorkId = 1;
    // Current logical date of the library
    private int currentDate = 0;
    
=======
    private int _currentDate = 1;

    private List <User> _users = new ArrayList<>();
>>>>>>> 90dee6fa8b40c8f729c236159352f290a4893f73

    //FIXME maybe define attributes
    //FIXME maybe implement constructor
    //FIXME maybe implement methods

    public int getCurrentDate() {
        return _currentDate;
    }

    public void advanceDate(int days) {
        _currentDate += days;
        updateUserStatus();
    }

    private void updateUserStatus() {
        //FIXME implement method
    }

    public void addUser(User user) {
        _users.add(user);
    }

    public User getUser(int id) {
        for (User user : _users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public String showUser(int id) {
        User user = getUser(id);  // Usar método existente
        return user.toDisplayString();
    }
    
    /**
     * Read the text input file at the beginning of the program and populates the
     * instances of the various possible types (books, DVDs, users).
     *
     * @param filename name of the file to load
     * @throws UnrecognizedEntryException
     * @throws IOException
     * FIXME maybe other exceptions
     */
    void importFile(String filename) throws UnrecognizedEntryException, IOException
	    /* FIXME maybe other exceptions */ 
    {
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.isBlank()) continue;
                    // The import spec uses ':' as the delimiter
                    String[] fields = line.split(":");

                    registerEntry(fields);
                

                }
            }
        }

        /** Dispatch a parsed line into concrete entity creation. */
        private void registerEntry(String... fields) throws UnrecognizedEntryException {
            String tag = fields[0];
            switch (tag) {
                case "BOOK":
                case "LIVRO":
                    // BOOK:title:authors:price:category:ISBN:copies
                    registerBook(fields);
                    break;
                case "DVD":
                    // DVD:title:director:price:category:IGAC:copies
                    registerDvd(fields);
                    break;
                case "USER":
                    // USER:name:email
                    //registerUser(fields);
                    break;
                default:
                    throw new UnrecognizedEntryException(tag);
            }
        }

        //implementar book already exist
        private void registerBook(String... fields) throws UnrecognizedEntryException {
            try {
              
                String title = fields[1];
                String authorsField = fields[2];
                int price = Integer.parseInt(fields[3]);
                Category category = Category.valueOf(fields[4].toUpperCase());
                String isbn = fields[5];
                int total = Integer.parseInt(fields[6]);

                int id = nextWorkId++;
                Book book = new Book(id, title, category, total, isbn, price);

                if (!authorsField.isBlank()) {
                    // Authors are separated by commas; allow spaces around commas
                    String[] creators = authorsField.split(",");
                    for (String name : creators) {
                        name = name.trim();
                        if (name.isEmpty()) continue;
                        Creator c = getOrCreateCreator(name);
                        book.addCreator(c);
                    }
                }
                addWork(book);
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                throw new UnrecognizedEntryException("BOOK", e);
            }
        }

        private void registerDvd(String... fields) throws UnrecognizedEntryException {
            try {
                if (fields.length < 7) {
                    throw new ArrayIndexOutOfBoundsException("DVD requires 7 fields after tag");
                }
                String title = fields[1];
                String directorName = fields[2];
                int price = Integer.parseInt(fields[3]);
                Category category = Category.valueOf(fields[4].toUpperCase());
                String igac = fields[5];
                int total = Integer.parseInt(fields[6]);
                Creator director = getOrCreateCreator(directorName);
                int id = nextWorkId++;
                DVD dvd = new DVD(id, title, category, total, igac, director, price);
                addWork(dvd);
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                throw new UnrecognizedEntryException("DVD", e);
            }
        }

        // // Minimal USER handler so USER lines in the initial file do not fail the import.
        // private void registerUser(String... fields) throws UnrecognizedEntryException {
        //     try {
        //         if (fields.length < 3) {
        //             throw new ArrayIndexOutOfBoundsException("USER requires name and email");
        //         }
        //         String name = fields[1].trim();
        //         String email = fields[2].trim();
        //         if (!name.isEmpty()) {
        //             userMap.put(name, email);
        //         }
        //     } catch (ArrayIndexOutOfBoundsException e) {
        //         throw new UnrecognizedEntryException("USER", e);
        //     }
        // }


            //this is test cod



    

/** Returns all works in the library, sorted by their ID. */
    public List<Work> getAllWorks() 
    {
        List<Work> result = new ArrayList<>(workMap.values());
        result.sort(Comparator.comparingInt(Work::getId));
        return result;
    }

   
    

    /** Returns the creator with the given name, creating it if it doesn't exist. 
     * @param name the name of the creator
     * @return the Creator object
     * use case: when adding a work, if the creator does not exist, create it
    */
    public Creator getOrCreateCreator(String name)
    {
        Creator creator = creatorMap.get(name);
        if (creator == null) {
            creator = new Creator(name);
            creatorMap.put(name, creator);
        }
        return creator;
    }

    /** Returns the creator with the given name, or null if it doesn't exist. 
     * @param name the name of the creator
     * @return the Creator object or null if not found
    */
    public Creator getCreator(String name) {
        Creator creator = creatorMap.get(name);
        return creator;
    }



    /** Adds a work to the library. */
    public void addWork(Work work) {
        workMap.put(work.getId(), work);
          for (Creator creator : work.getCreators())
           {
      
             creatorMap.putIfAbsent(creator.getName(), creator);
            
           }
           
    }
    
    
    /**
   * Retrieves a work directly by its unique ID.
   *
   * @param id the unique ID of the work.
   * @return the Work object.
   * @throws UnknownWorkException if no work with the given ID is found.
   */
  public Work getWork(int id) /**throws UnknownWorkException*/ 
  {
      Work work = workMap.get(id);
      if (work == null) {
          //throw new UnknownWorkException(id);
      }
      return work;
  }
    
  
     public void removeWork(Work work) {
        List<Creator> creators = work.getCreators();
        workMap.remove(work.getId());

        for (Creator creator : creators) {
            creator.removeWork(work);
            if (!creator.hasWorks()) {
                creatorMap.remove(creator.getName());
            }
        }
    }



    /**
     * Searches for works in the library that match the given search term.
     *
     * Default search implementation that checks titles and creators.
     * @param term the search term to match against work titles and creators
     * @return a list of works that match the search term
     */
    public List<Work> searchWorks(String term) 
    {
        List<Work> result = new ArrayList<>();
        for (Work w : workMap.values()) {
            if (new DefaultSearch(w).search(term)) 
            {
                result.add(w);
            }
        }
        result.sort(Comparator.comparingInt(Work::getId));
        return result;
    }


    // Search by price using PriceSearch
    public List<Work> searchWorksByPrice(int price) 
    {
        List<Work> result = new ArrayList<>();
        String term = Integer.toString(price);
        for (Work w : workMap.values()) {
            if (new SearchByPrice(w).search(term)) 
            {
                result.add(w);
            }
        }
        result.sort(Comparator.comparingInt(Work::getId));
        return result;
    }

  

    // Search by creator using CreatorSearch
    public List<Work> searchWorksByCreator(String creatorName) 
    {
        List<Work> result = new ArrayList<>();
        for (Work w : workMap.values()) 
        {
            if (new SearchByCreator(w).search(creatorName)) 
            {
                result.add(w);
            }
        }
            // Ordenar por título, ignorando maiúsculas/minúsculas; em caso de empate, por id
            result.sort(Comparator
                .comparing((Work w) -> w.getTitle().toLowerCase())
                .thenComparingInt(Work::getId));
            return result;
    }

    
    
    
    
    /**
     * Updates the inventory for a specific work item.
     *
     * @param work   the work item to update
     * @param amount the amount to update the inventory by (can be negative)
     */
    public void updateInventory(Work work, int amount) throws InvalidInventoryUpdateException {
        if (work == null) {
            return; // or throw unknown work later if required
        }
        if (!work.updateInventory(amount)) {
            throw new InvalidInventoryUpdateException();
        }
        if (work.getTotalCopies() == 0) {
            removeWork(work);

        }
    }

    /** Returns the current logical date. */
    public int getCurrentDate() {
        return currentDate;
    }

    /** Advances the current logical date by a positive number of days. */
    public void advanceDate(int days) {
        if (days > 0) {
            currentDate += days;
        }
    }





}
