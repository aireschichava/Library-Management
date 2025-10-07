package bci.works;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  Class that represents a work (book, DVD, etc.) in the library.
 */
public abstract class Work implements Serializable {
		@java.io.Serial
		private static final long serialVersionUID = 202507171003L;
		
		private int id;
		private String title;
		private List<Creator> creators;
		private int totalCopies;
		private int availableCopies;
		private Category category;
		private int price;

		public Work(int id, String title, Category category, int totalCopies, int price) {
			this.id = id;
			this.title = title;
			this.category = category;
			this.totalCopies = totalCopies;
			this.availableCopies = totalCopies;
			this.price = price;
			this.creators = new ArrayList<>();
		}

		public int getId() {
			return id;
		}

		public String getTitle() {
			return title;
		}

		public List<Creator> getCreators() {
			return creators;
		}

		public int getTotalCopies() {
			return totalCopies;
		}

		public int getAvailableCopies() {
			return availableCopies;
		}

		public Category getCategory() {
			return category;
		}
		public int getPrice() {
			return price;
		}

		public boolean isAvailable() {
			return availableCopies > 0;
		}

		public abstract void addCreator(Creator creator);




		/** Updates the inventory for this work item.
		 *
		 * @param amount the amount to update the inventory by (can be negative)
		 * @return true if the update was successful, false otherwise
		 */

		public boolean updateInventory(int amount) {
			if (availableCopies + amount >= 0 && totalCopies + amount >= 0) {
				availableCopies += amount;
				totalCopies += amount;
				return true;
			}
			return false;
		}


		public abstract boolean search(String term);


}
