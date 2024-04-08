package angel.Count.Service;

import angel.Count.Entity.Count;

import java.util.List;

public interface ICountService {

    public Count findCountById(Integer id);
    public List<Count> listCounts();
    public void saveCount(Count count);

    public void deleteCount(Count count);
}
