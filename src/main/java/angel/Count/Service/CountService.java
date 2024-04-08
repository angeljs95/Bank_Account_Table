package angel.Count.Service;

import angel.Count.Entity.Count;
import angel.Count.Repository.CountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountService implements ICountService {

    @Autowired
    private CountRepository countRepository;

    @Override
    public Count findCountById(Integer id) {
        Count count = countRepository.findById(id).orElse(null);
        return count;
    }

    @Override
    public List<Count> listCounts() {
        return countRepository.findAll();
    }

    @Override
    public void saveCount(Count count) {
        countRepository.save(count);
    }

    @Override
    public void deleteCount(Count count) {
        countRepository.delete(count);
    }
}
