package DictionaryProcesser;

import java.util.TreeMap;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import DictionaryProcesser.DocumentWordCounter;

public final class Dictionary extends TreeMap<String, DocumentWordCounter>
{
    public boolean process_raw_text(String filePath)
    {
        String rawText = this.read_from_file(filePath);
        
        if (this.text_validation(rawText) == true)
        {

            String[] sentLst = rawText.toLowerCase().split("\\.");

            for (String sent : sentLst)
            {
                add_sent(filePath, sent);
            }
            return true;
        } 

        return false;
    }

    private boolean text_validation(String rawText)
    {
        // Есть детектер языка https://detectlanguage.com/ но мы пройдемся по юникоду

        Boolean bol = true;

        for (Integer i = 0; i < rawText.length(); i++)
        {
            char symb = rawText.charAt(i);

            if ((symb >= (char)0x0020 && symb <= (char)0x0040) ||   // Знаки припянания
            (symb >= 0x0041 && symb <= 0x007E) ||                   // Английский язык
             (symb >= (char)0x0410 && symb <= (char)0x044F) ||      // Русский язык
             (symb >= (char)0x00A1 && symb <= (char)0x00BB) ||      // Всякие стрелочки             
             (symb >= (char)0x0451 && symb <= (char)0x0451))        // Буква ё
            {
                
            }
            else 
            {
                System.out.println(" Stopchar: " + symb);
                bol = false;
            }
             
        }

        return bol;
    }

    private synchronized boolean add_sent(String filePath, String sent)
    {
        
        String[] words = sent.replaceAll("[.,!?\"\']()","").split(" ");

        for (String word : words)
        {
            if (word != "")
            {
                DocumentWordCounter dwc = this.get(word);
                if (dwc == null) 
                {
                    this.put(word, new DocumentWordCounter().increment_document_counter(filePath));
                }
                else {
                    dwc.increment_document_counter(filePath);
                }
            }

        }
        return true;
    }


    public boolean compute_total_word_count()
    {
        // В VSCode не пашет этот foreach((((
            
        // for (Map.Entry<String,DocumentWordCounter> entry : this.entrySet())
        // {
        //     System.out.println(" word: " + entry.getKey() + " | Count: " +  entry.getValue().compute_total_word_count().toString());
        // }

        return true;
    }

    public String read_from_file(String filePath)
    {
        String rawText = "";
        
        try 
        {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line = "";
            while ((line = reader.readLine()) != null)
            {
                rawText = rawText.concat(" " + line);
            }         
            reader.close(); 
        }
        catch (IOException e)
        {
            System.out.println("Reading error, filename: " + filePath);
            System.out.println(e.getMessage());
        }
        return rawText;
    }
}