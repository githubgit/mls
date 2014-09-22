package net.tap5.mls.model;


import org.apache.tapestry5.Block;
 
public interface TabGroupModel {
   void addTab(String name, String label, Block body);
}
