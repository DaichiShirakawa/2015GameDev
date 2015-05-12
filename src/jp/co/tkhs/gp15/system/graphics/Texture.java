/*
 * Copyright (c) 2002-2008 LWJGL Project All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 * 
 * * Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 * 
 * * Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided with
 * the distribution.
 * 
 * * Neither the name of 'LWJGL' nor the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package jp.co.tkhs.gp15.system.graphics;

import static org.lwjgl.opengl.GL11.*;

public class Texture {
    private int target;
    private int textureID;

    private int width;
    private int height;

    private int texWidth;
    private int texHeight;

    private boolean isAlphaPremultiplied;

    public Texture(final int target, final int textureID) {
        this.target = target;
        this.textureID = textureID;
        this.isAlphaPremultiplied = true;
    }

    public final void point(final int srcX, final int srcY) {
        float tx = 1.0f * srcX / texWidth;
        float ty = 1.0f * srcY / texHeight;

        glTexCoord2f(tx, ty);
    }

    public final int getTextureID() {
        return textureID;
    }

    public final void setTextureHeight(final int texHeight) {
        this.texHeight = texHeight;
    }

    public final void setTextureWidth(final int texWidth) {
        this.texWidth = texWidth;
    }

    public final int getTextureWidth() {
        return texWidth;
    }

    public final int getTextureHeight() {
        return texHeight;
    }

    public final int getWidth() {
        return width;
    }

    public final void setWidth(final int width) {
        this.width = width;
    }

    public final int getHeight() {
        return height;
    }

    public final void setHeight(final int height) {
        this.height = height;
    }

    public final boolean isAlphaPremultiplied() {
        return isAlphaPremultiplied;
    }

    public final void setAlphaPremultiplied(final boolean isAlphaPremultiplied) {
        this.isAlphaPremultiplied = isAlphaPremultiplied;
    }

    public final void dispose() {
        if (0 < textureID) {
            glDeleteTextures(textureID);
            textureID = -1;
        }
    }

    public final void bind() {
        glBindTexture(target, textureID);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
    }

}
