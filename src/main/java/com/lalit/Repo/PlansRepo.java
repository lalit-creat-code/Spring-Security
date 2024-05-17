package  com.lalit.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lalit.entity.PlansApp;

@Repository
public interface PlansRepo extends JpaRepository<PlansApp,Integer> {
	

}
