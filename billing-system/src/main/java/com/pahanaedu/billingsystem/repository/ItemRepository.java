        package com.pahanaedu.billingsystem.repository;

        import org.springframework.data.mongodb.repository.MongoRepository;
        import org.springframework.stereotype.Repository;

        import com.pahanaedu.billingsystem.model.Item;

        @Repository
        public interface ItemRepository extends MongoRepository<Item, String> { // Change Long to String
            // You might have custom query methods here
        }
        