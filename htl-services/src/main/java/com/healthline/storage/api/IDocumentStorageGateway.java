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

    String retrieveFile(String url);
}
