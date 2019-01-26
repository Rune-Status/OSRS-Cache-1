/*
GNU LGPL V3
Copyright (C) 2019 Bart van Helvert
B.A.J.v.Helvert@gmail.com

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with this program; if not, write to the Free Software Foundation,
Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package io.github.bartvhelvert.jagex.fs.osrs.dict

import io.github.bartvhelvert.jagex.fs.Dictionary
import io.github.bartvhelvert.jagex.fs.DictionaryCompanion
import io.github.bartvhelvert.jagex.fs.JagexCache
import io.github.bartvhelvert.jagex.fs.osrs.plane.Widget

class WidgetDictionary(
    val interfaces: List<Widget>
) : Dictionary {

    companion object : DictionaryCompanion<WidgetDictionary>() {
        override val id = 3

        @ExperimentalUnsignedTypes
        fun load(cache: JagexCache): WidgetDictionary {
            val widgets = mutableListOf<Widget>()
            cache.readArchives(SpriteDictionary.id).forEach { archiveId, archive ->
                archive.files.forEach { fileId, file ->
                    val widgetId = (archiveId shl 16) + fileId
                    widgets.add(Widget.decode(widgetId, file.data)) //TODO fix decoding
                }
            }
            return WidgetDictionary(widgets)
        }
    }
}