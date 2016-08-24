/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.application.options.colors;

import com.intellij.codeHighlighting.RainbowHighlighter;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.colors.EditorSchemeAttributeDescriptorWithPath;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

class RainbowAttributeDescriptor implements EditorSchemeAttributeDescriptorWithPath {
  private final String myGroup;
  private final ColorAndFontGlobalState myColorAndFontGlobalState;
  private final String myDisplayName;
  private final EditorColorsScheme myScheme;
  private final Language myLanguage;
  private final RainbowColorsInSchemeState myRainbowColorsInSchemaState;

  public ColorAndFontGlobalState getColorAndFontGlobalState() {
    return myColorAndFontGlobalState;
  }

  public RainbowAttributeDescriptor(@Nullable Language language,
                                    @NotNull ColorAndFontGlobalState colorAndFontGlobalState,
                                    @NotNull String group,
                                    @NotNull String displayNameWithPath,
                                    @NotNull EditorColorsScheme scheme,
                                    @NotNull RainbowColorsInSchemeState rainbowState) {
    myLanguage = language;
    myColorAndFontGlobalState = colorAndFontGlobalState;
    myDisplayName = displayNameWithPath;
    myRainbowColorsInSchemaState = rainbowState;
    myScheme = scheme;
    myGroup = group;
  }

  @Override
  public String toString() {
    return myDisplayName;
  }

  @Override
  public String getGroup() {
    return myGroup;
  }

  @Override
  public String getType() {
    return RainbowHighlighter.RAINBOW_TYPE;
  }

  @Override
  public EditorColorsScheme getScheme() {
    return myScheme;
  }

  @Override
  public void apply(@NotNull EditorColorsScheme scheme) {
    if (myLanguage == null) {
      myRainbowColorsInSchemaState.apply(scheme);
    }
    // see myColorAndFontGlobalState apply
  }

  @Override
  public boolean isModified() {
    return (myLanguage == null && myRainbowColorsInSchemaState.isModified())
           || myColorAndFontGlobalState.isModified(myLanguage);
  }

  public List<Pair<Boolean, Color>> getRainbowColorsInSchemaState() {
    return myRainbowColorsInSchemaState.getInheritanceAndColors();
  }

  public Color getDefaultColor(int index) {
    return RainbowHighlighter.RAINBOW_COLOR_KEYS[index].getDefaultAttributes().getForegroundColor();
  }

  public Language getLanguage() {
    return myLanguage;
  }
}
