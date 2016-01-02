/**
 * 
 */
package com.healthline.storage.api;

/**
 * @author Aniket
 * 
 */
public interface IDocumentStorageGateway
{
    String storeFile(String fileName, byte[] content);

    byte[] retrieveFile(String url);
}
