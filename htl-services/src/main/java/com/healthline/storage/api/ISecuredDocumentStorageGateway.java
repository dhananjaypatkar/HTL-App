/**
 * 
 */
package com.healthline.storage.api;

/**
 * @author Aniket
 * 
 */
public interface ISecuredDocumentStorageGateway
        extends IDocumentStorageGateway
{
    String encryptAndStoreFile(String fileName, byte[] content, String password);

    String retrieveAndDescryptFile(String url, String password);
}
