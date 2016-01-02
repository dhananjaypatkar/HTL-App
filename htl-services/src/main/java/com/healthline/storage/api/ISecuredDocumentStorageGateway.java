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

    byte[] retrieveAndDecryptFile(String url, String password);
}
