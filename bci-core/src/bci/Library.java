package bci;

import bci.exceptions.*;
import bci.rules.*;
import bci.search.DefaultSearch;
import bci.search.SearchByCreator;
import bci.search.SearchByPrice;
import bci.user.User;
import bci.works.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that represents the library as a whole.
 */
class Library implements Serializable {

    /** Serial version UID for serialization. */
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    /** Map of creator names to Creator objects. */
    private Map<String, Creator> creatorsMap = new HashMap<>();
    /** Map of work IDs to Work objects. */
    private Map<Integer, Work> worksMap = new HashMap<>();
    /** User registry (id -> User). */
    private Map<Integer, User> usersMap = new HashMap<>();
    /** Auto-increment id for imported works (since text format has no id). */
    /**Map for work Loans*/
    private Map<Integer, Loan> loansMap = new HashMap<>();
    private int nextWorkId = 1;
    /** Auto-increment id for users. */
    private int nextUserId = 1;
    /** Auto-increment id for loans */
    private int currentDate = 1;

    /**
     * Read the text input file at the beginning of the program and populates the
     * instances of the various possible types (books, DVDs, users).
     *
     * @param filename name of the file to load
     * @throws UnrecognizedEntryException if an entry is not recognized
     * @throws IOException if an I/O error occurs
     */
    void importFile(String filename) throws UnrecognizedEntryException, IOException {
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

    /**
     * Dispatch a parsed line into concrete entity creation.
     * @param fields the fields parsed from the input line
     * @throws UnrecognizedEntryException if the entry type is not recognized
     */
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
                    registerUser(fields);
                    break;
                default:
                    throw new UnrecognizedEntryException(tag);
            };
        }

    /**
     * Registers a book entry from parsed fields.
     * @param fields the fields parsed from the input line
     * @throws UnrecognizedEntryException if the entry is invalid
     */
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

    /**
     * Registers a DVD entry from parsed fields.
     * @param fields the fields parsed from the input line
     * @throws UnrecognizedEntryException if the entry is invalid
     */
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
                int id = nextWorkId++;
                DVD dvd = new DVD(id, title, category, total, igac, price);
                addWork(dvd);
                dvd.addCreator(getOrCreateCreator(directorName));
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                throw new UnrecognizedEntryException("DVD", e);
            }
        }

    /**
     * Handle USER lines: USER:name:email
     * @param fields the fields parsed from the input line
     * @throws UnrecognizedEntryException if the entry is invalid
     */
        private void registerUser(String... fields) throws UnrecognizedEntryException {
            try {
                if (fields.length < 3) {
                    throw new ArrayIndexOutOfBoundsException("USER requires name and email");
                }
                String name = fields[1].trim();
                String email = fields[2].trim();
                if (!name.isEmpty()) {
                    User user = new User(getNextUserId(), name, email);
                    addUser(user);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new UnrecognizedEntryException("USER", e);
            }
        }

/** Returns all works in the library, sorted by their ID.
     * @return list of all works
     */
    public List<Work> getAllWorks() 
    {
        List<Work> result = new ArrayList<>(worksMap.values());
        result.sort(Comparator.comparingInt(Work::getId));
        return result;
    }

   
    /**
     * Returns the creator with the given name, creating it if it doesn't exist.
     * @param name the name of the creator
     * @return the Creator object
     * use case: when adding a work, if the creator does not exist, create it
    */
    public Creator getOrCreateCreator(String name)
    {
        Creator creator = creatorsMap.get(name);
        if (creator == null) {
            creator = new Creator(name);
            creatorsMap.put(name, creator);
        }
        return creator;
    }

    /**
     * Returns the creator with the given name, or null if it doesn't exist.
     * @param name the name of the creator
     * @return the Creator object or null if not found
    */
    public Creator getCreator(String name) {
        Creator creator = creatorsMap.get(name);
        return creator;
    }



    /**
     * Adds a work to the library.
     * @param work the work to add
     */
    public void addWork(Work work) {
        worksMap.put(work.getId(), work);
          for (Creator creator : work.getCreators())
           {
      
             creatorsMap.putIfAbsent(creator.getName(), creator);
            
           }
           
    }
    
    
    /**
   * Retrieves a work directly by its unique ID.
   *
   * @param id the unique ID of the work
   * @return the Work object, or null if not found
   */
  public Work getWork(int id)
  {
      Work work = worksMap.get(id);
      if (work == null) {
          
      }
      return work;
  }
    
  
     /**
     * Removes a work from the library and its creators.
     * @param work the work to remove
     */
    public void removeWork(Work work) {
        List<Creator> creators = work.getCreators();
        worksMap.remove(work.getId());

        for (Creator creator : creators) {
            creator.removeWork(work);
            if (!creator.hasWorks()) {
                creatorsMap.remove(creator.getName());
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
        for (Work w : worksMap.values()) {
            if (new DefaultSearch(w).search(term)) 
            {
                result.add(w);
            }
        }
        result.sort(Comparator.comparingInt(Work::getId));
        return result;
    }


    /**
     * Search by price using PriceSearch.
     * @param price the price to search for
     * @return a list of works that match the price
     */
    public List<Work> searchWorksByPrice(int price) 
    {
        List<Work> result = new ArrayList<>();
        String term = Integer.toString(price);
        for (Work w : worksMap.values()) {
            if (new SearchByPrice(w).search(term)) 
            {
                result.add(w);
            }
        }
        result.sort(Comparator.comparingInt(Work::getId));
        return result;
    }

    /**
     * Search by creator using CreatorSearch.
     * @param creatorName the name of the creator
     * @return a list of works by the creator
     */
    public List<Work> searchWorksByCreator(String creatorName) 
    {
        List<Work> result = new ArrayList<>();
        for (Work w : worksMap.values()) 
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
     * we add more new copies or remove existing copies from the work.
     *
     * @param work   the work item to update
     * @param amount the amount to update the inventory by (can be negative)
     * @throws InvalidInventoryUpdateException if the update is invalid
     */
    public void updateInventory(Work work, int amount) throws InvalidInventoryUpdateException {
        if (work == null) {
            return;
        }
        if (!work.updateInventory(amount)) {
            throw new InvalidInventoryUpdateException();
        }
        if (work.getTotalCopies() == 0) {
            removeWork(work);

        }
    }

    /**
     * Returns the current logical date.
     * @return the current date
     */
    public int getCurrentDate() {
        return currentDate;
    }

    /**
     * Advances the current logical date by a positive number of days.
     * @param days number of days to advance
     */
    public void advanceDate(int days) {
        if (days > 0) {
            currentDate += days;
            updateUserStatus();
        }
    }

    
    /**
     * Returns the next available user ID and increments the counter.
     * @return the next user ID
     */
    public int getNextUserId() { return nextUserId++; }

    /**
     * Adds a user to the library.
     * @param user the user to add
     */
    public void addUser(User user) { if (user != null) usersMap.put(user.getId(), user); }

    /**
     * Returns the user with the given ID.
     * @param id the user ID
     * @return the User object or null if not found
     */
    public User getUser(int id) { return usersMap.get(id); }

    /**
     * Returns a display string for the user with the given ID.
     * @param id the user ID
     * @return display string or null if user not found
     */
    public String showUser(int id) {
        User user = usersMap.get(id);
        return user == null ? null : user.toDisplayString();
    }

    /**
     * Returns all users in the library, sorted by name and ID.
     * @return list of all users
     */
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>(usersMap.values());
        result.sort(Comparator.comparing(User::getName, String.CASE_INSENSITIVE_ORDER)
                              .thenComparingInt(User::getId));
        return result;
    }



    /**
     * Return a work borrowed by a user.
     * @param userId user ID
     * @param workId work ID
     * @return true if successful
     */
    public boolean returnWork(int userId, int workId) {
        User user = getUser(userId);
        Work work = getWork(workId);
        
        if (user == null || work == null) return false;

        Loan loan = findActiveLoan(userId, workId);
        if (loan == null) return false;

        loan.returnWork(currentDate);
        boolean onTime = loan.isReturnedOnTime();

        if (!onTime) {
            int daysLate = loan.getDaysLate(currentDate);
            user.addFine(daysLate * 5);
            user.setSuspended();
        }

        user.recordReturn(onTime);
        work.incrementAvailableCopies();

        return true;
    }

    public Loan findActiveLoan(int userId, int workId) {
        return loansMap.values().stream()
            .filter(l -> l.getUserId() == userId && 
                        l.getWorkId() == workId && 
                        l.isActive())
            .findFirst()
            .orElse(null);
    }

    /**
     * Process the return and produce a small result with details needed by the UI.
     */
    public ReturnResult returnWorkWithResult(int userId, int workId) {
        User user = getUser(userId);
        Work work = getWork(workId);
        if (user == null || work == null) return new ReturnResult(false, 0, false);

        Loan loan = findActiveLoan(userId, workId);
        if (loan == null) return new ReturnResult(false, 0, false);

    int copiesBeforeReturn = work.getAvailableCopies();
    int today = this.currentDate;
    loan.returnWork(today);
    boolean onTime = loan.isReturnedOnTime();
        int fine = 0;
        if (!onTime) {
            int daysLate = loan.getDaysLate(today);
            fine = daysLate * 5;
            user.addFine(fine);
            user.setSuspended();
            // Return the user's total fine (cumulative) so the UI can display the
            // updated amount to be paid, matching the expected test outputs.
            fine = user.getFine();
        }

        user.recordReturn(onTime);
        work.incrementAvailableCopies();

        boolean notified = false;
        if (copiesBeforeReturn == 0 && work.getAvailableCopies() > 0) {
            work.notifyObservers(work.message());
            notified = true;
        }

        return new ReturnResult(true, fine, notified);
    }
    /**
     * Applies a fine to a user.
     * @param user the user to apply the fine to
     */
    public void applyFine(User user){
        user.addFine(5);
    }
   /**
     * Updates user statuses based on overdue loans.
     * This method checks all active loans and suspends users with overdue loans.
     * we first iterate through all loans in the loansMap. If a loan is found to be overdue (i.e., not returned and past its due date),
     * we retrieve the associated user and set their status to suspended.
     * we use lambda expressions and the forEach method to streamline the iteration and condition checking.
     */
    private void updateUserStatus() {

        loansMap.values().forEach(loan -> {
            if (!loan.isReturned() && loan.getDueDate() < currentDate) {

                User user = getUser(loan.getUserId());
                if (user != null) {
                    user.setSuspended();
                }
            }
            
        }
         
        );

        usersMap.values().forEach(this::ReactivateUser);
        //“For each element in the collection, call ReactivateUser with that element as argument.”
    		
    }


    /**
     * Reactivates a user if they are suspended, have no fines, and no overdue loans.
     * @param user the user to check and potentially reactivate
     */
    private void ReactivateUser(User user) {
        if (user.isSuspended() &&
            user.getFine() == 0 &&
            user.getLoans().stream().noneMatch(loan -> loan.isOverdue(currentDate))) {
            user.setActive();
        }
    }

    /**
     * Retrieves the notifications for a user by their ID.
     * @param id the user ID
     * @return list of notifications for the user
     * we first retrieve the User object from the usersMap using the provided ID.
     * Then, we call the getNotifications method on the User object to obtain their notifications.
     */
    public List<String> getUserNotifications(int id) {
    
        return usersMap.get(id).getNotifications();
    }

    /**
     * Removes all notifications for a user by their ID.
     * @param id the user ID
     * we first retrieve the User object from the usersMap using the provided ID.
     * Then, we call the clearNotifications method on the User object to remove their notifications.
     */
    public void clearNotifications(int id) {
        User user = usersMap.get(id);
        if (user != null) {
            user.clearNotifications();
        }
    }

    /**
	 * Validates the request rules for a user requesting a work.
	 * @param user the user making the request
	 * @param work the work being requested
	 * @return the ID of the failed rule, or 0 if all rules pass
	 */
    public int validateRules(User user, Work work) {
		
		RequestRule rule = new CompositeRequestRule();
		//cast the rule to CompositeRequestRule to access addRules method and then acreate instances of each rule and add them to the composite
		((CompositeRequestRule) rule).addRules(
				new DuplicateRequestRule(),
				new SuspendedUserRule(),
				new WorkAvailabilityRule(),
				new MaxRequestsRule(),
				new ReferenceWorkRule()
		);
		
		if (!rule.validate(user, work))
			return rule.getId();
		return 0;
    }
    
    /**
	 * Processes a loan request for a user and work.
	 * @param user the user making the request
	 * @param work the work being requested
	 * @param requestDate the date of the request
	 * @return the loan ID
	 * according to the user's behavior and the number of copies of the work, we determine the due date for the loan.
	 */
    public int loanWork(User user, Work work, int requestDate) {
    	
    	int dueDate=user.getBehavior().loanPeriodForCopies(work.getTotalCopies())+requestDate;
        int loanId = loansMap.size() + 1;
        
        Loan loan = new Loan(loanId, user.getId(), work.getId(), requestDate, dueDate);
        loansMap.put(loanId, loan);
        user.setLoans(loan);
        work.decrementAvailableCopies();
        return dueDate;
    }

    /**
     * Adds a user as an observer to a work.
     */
    public void addObserver(User user, Work work) {
  
        work.addObserver(user);
    }

    public boolean payFine(User user) {
        // If the user is not suspended, there is no fine to pay — signal failure
        // so the UI command can throw UserIsActiveException with the proper message.
        if (!user.isSuspended()) {
            return false;
        }

        // Clear the fine and reactivate the user if there are no overdue loans.
        user.clearFine();

        boolean hasOverdueLoans = loansMap.values().stream()
        .anyMatch(loan -> loan.getUserId() == user.getId() && 
                         loan.isActive() && 
                         loan.isOverdue(currentDate));

        if (!hasOverdueLoans) {
            user.setActive();
            return true;
        }

        return false;
    }

}