package DataBaseController;

import java.util.TreeSet;
import java.util.Set;

import java.sql.ResultSet;
import java.sql.SQLException;

import DataBaseController.TableCrud.DBFileCrud;
import DataBaseController.TableCrud.DBWordCrud;
import DataBaseController.TableCrud.DBWordFileMappingCrud;

import DictionaryProcesser.Dictionary;
import java.util.ArrayList;

import Logger.Logger;

public class DBDictionaryController {
     
    private static DBWorker dbWorker = new DBWorker();


    public static boolean insert_dictionary(Dictionary treeDict)
    {
        boolean resStatus = false;

        // ResultSet resWrd = DBWordCrud.dbcrud_word_browse(dbWorker, "back");
        // Integer wordID = -1;
        // try
        // {   
        //     resWrd.next();
        //     wordID = resWrd.getInt("WordID");
        //     System.out.println(wordID);
        //     resWrd.close();
        // }
        // catch (SQLException e)
        // {
        //     System.out.println(e);
        // }


        // try
        // {
        //     ResultSet resFile = DBFileCrud.dbcrud_file_browse(dbWorker, "D:\\Programming\\VS code\\Projects\\Java prj\\hellomaven\\InputText\\folder1\\thirdTxtFile");
            
        //     System.out.println(resFile.isLast());

        //     resFile.next();
        //     String fileID = resFile.getString("FileID");
        //     resFile.close();

        //     System.out.println(fileID);

        // }
        // catch (SQLException e)
        // {
        //     System.out.println(e);
        // }

        

        Set<String> words = treeDict.keySet();
        TreeSet<String> files = new TreeSet();

        for (String word : words)
        {
            files.addAll(treeDict.get(word).keySet());
        }

        dbWorker.test_connection();

        DBWordCrud.dbcrud_word_mass_insert(dbWorker, words);
        DBFileCrud.dbcrud_file_mass_insert(dbWorker, (Set)files);

        for (String word : words)
        {
            Logger.setLog(word);
            files.clear();
            files.addAll(treeDict.get(word).keySet());
            ResultSet resWrd = DBWordCrud.dbcrud_word_browse(dbWorker, word);
            Integer wordID = -1;
            try
            {
                if (resWrd.next())
                {
                    wordID = resWrd.getInt("WordID");
                    Logger.setLog("wordID: " + wordID);
                    resWrd.close();
                }
                else
                {
                    Logger.setLog("wordID : WTF");
                }
            }
            catch (SQLException e)
            {
                Logger.setLog("wordID : " + e);
            }


            for (String file : files)
            {
                Logger.setLog("|" + file + "|" + " Count:" + treeDict.get(word).get(file));
                try
                {
                    ResultSet resFile = DBFileCrud.dbcrud_file_browse(dbWorker, file);
                    resFile.next();
                    Integer fileID = resFile.getInt("FileID");
                    resFile.close();

                    System.out.println(fileID);
                    Logger.setLog("fileID: " + fileID);

                    DBWordFileMappingCrud.dbcrud_wordFileMapping_insert(dbWorker, fileID, wordID, treeDict.get(word).get(file));
                }
                catch (SQLException e)
                {

                    System.out.println(e);
                }
                catch (NullPointerException e)
                {
                    Logger.setLog("exepct: " + file);
                    Logger.setLog(e.toString()); 
                }
            }
        }

        return resStatus;
    }

    
    public static ArrayList get_files_for_word(String searchWord)
    {
        Logger.setLog("searchWord: " + searchWord);
        ArrayList<String> result = new ArrayList<>();

        DBWorker dbWorker = new DBWorker();
        dbWorker.test_connection();

        ResultSet resWrd = DBWordCrud.dbcrud_word_browse(dbWorker, searchWord);

        Integer wordID = -1;
        try
        {
            if (resWrd.next())
            {
                wordID = resWrd.getInt("WordID");
                Logger.setLog("wordID: " + wordID);
                resWrd.close();
            }
        }
        catch (SQLException e)
        {
            Logger.setLog("wordID : " + e);
        }

        ResultSet resWrdFilMap = DBWordFileMappingCrud.dbcrud_wordFileMapping_browse(dbWorker, wordID);

        Integer fkFileID = -1;
        Integer count = -1;
        try
        {
            while (resWrdFilMap.next())
            {
                fkFileID = resWrdFilMap.getInt("fkFileID");
                count = resWrdFilMap.getInt("Count");
                Logger.setLog("fkFileID: " + fkFileID);
                ResultSet resFile = DBFileCrud.dbcrud_file_browse(dbWorker, fkFileID);
                String filePath = "";
                
                if (resFile.next())
                {
                    filePath = resFile.getString("FilePath");
                    Logger.setLog("filePath: " + filePath + " " + count);
                    result.add(filePath + " " + count);
                    resWrd.close();
                }
                
            }
            resWrdFilMap.close();
        }
        catch (SQLException e)
        {
            Logger.setLog("SQLException : " + e);
        }
        catch (NullPointerException e)
        {
            Logger.setLog("NullPointerException : " + e);
        }

        return result;
    } 
}
