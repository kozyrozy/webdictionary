package DictionaryProcesser;

import java.util.TreeMap;

public class DocumentWordCounter extends TreeMap<String, Integer>
{
    
    public DocumentWordCounter increment_document_counter(String filePath)
    {
        Integer count = this.get(filePath);

        if (count == null)
        {
            this.put(filePath, 1);
        }
        else 
        {
            this.put(filePath, count + 1);
        }
        
        return this;
    }

    public Integer get_total_words_count()
    {
        Integer count = 0;

        // В VSCode не пашет этот foreach((((
        // for (Map.Entry<String,Integer> entry : this.entrySet())
        // {
        //     count += entry.getValue();
        // }
        
        return count;
    }
}
