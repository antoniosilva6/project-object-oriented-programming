package prr.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import prr.core.exception.ImportFileException;
import prr.core.exception.MissingFileAssociationException;
import prr.core.exception.UnavailableFileException;
import prr.core.exception.UnrecognizedEntryException;

import java.io.FileInputStream;

/**
 * Manage access to network and implement load/save operations.
 */

public class NetworkManager {

  /** The network itself. */
  private Network _network;
  
  /** Current filename. */
  private String _filename;

  public NetworkManager(){
    _filename = "";
    _network = new Network();
  }

  public Network getNetwork() {
    return _network;
  }
  
  public String getFilename(){
    return _filename;
  }

  public void setFilename(String name){
    _filename = name; 
  }

  /**
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   * @throws IOException
   * @throws ClassNotFoundException
   */

  public void load(String filename) throws UnavailableFileException, IOException, ClassNotFoundException {
    ObjectInputStream objIn = null;
    try {
      objIn = new ObjectInputStream(new FileInputStream(filename));
      _network = (Network) objIn.readObject();
        } finally {
          if (objIn != null)
          objIn.close();
    }
  }

  /**
   * Saves the serialized application's state into the file associated to the current network.
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */

  public void save() throws FileNotFoundException, MissingFileAssociationException, IOException {
    ObjectOutput obOut = null;
    if(_filename == null){ 
      throw new MissingFileAssociationException();
    }
    try {
      obOut = new ObjectOutputStream(new FileOutputStream(_filename));
      obOut.writeObject(_network);
    } catch (IOException e) {
      throw e;
    } finally {
      if(obOut != null)
      obOut.close();
    }
  }
  
  /**
   * Saves the serialized application's state into the specified file. The current network is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current network does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   */

  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    _filename = filename;
    save();
  }
  
  /**
   * Read text input file and create domain entities..
   * 
   * @param filename name of the text input file
   * @throws ImportFileException
   */

  public void importFile(String filename) throws ImportFileException {
    try {
      _network.importFile(filename);
    } catch (IOException | UnrecognizedEntryException e) {
      throw new ImportFileException(filename, e);
    } 
  }  
}
