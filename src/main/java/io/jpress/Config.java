/**
 * Copyright (c) 2015-2016, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jpress;

import io.jpress.core.Jpress;
import io.jpress.core.JpressConfig;
import io.jpress.message.Actions;
import io.jpress.message.MessageKit;
import io.jpress.ui.freemarker.function.*;
import io.jpress.ui.freemarker.tag.*;

public class Config extends JpressConfig {


    @Override
    public void onJPressStarted() {

        Jpress.addTag(ContentsTag.TAG_NAME, new ContentsTag());
        Jpress.addTag(ContentTag.TAG_NAME, new ContentTag());
        Jpress.addTag(ModulesTag.TAG_NAME, new ModulesTag());
        Jpress.addTag(TagsTag.TAG_NAME, new TagsTag());
        Jpress.addTag(TaxonomyTag.TAG_NAME, new TaxonomyTag());
        Jpress.addTag(TaxonomysTag.TAG_NAME, new TaxonomysTag());
        Jpress.addTag(ArchivesTag.TAG_NAME, new ArchivesTag());
        Jpress.addTag(UsersTag.TAG_NAME, new UsersTag());

        Jpress.addFunction("TAXONOMY_BOX", new TaxonomyBox());
        Jpress.addFunction("OPTION", new OptionValue());
        Jpress.addFunction("OPTION_CHECKED", new OptionChecked());
        Jpress.addFunction("OPTION_SELECTED", new OptionSelected());
        Jpress.addFunction("METADATA_CHECKED", new MetadataChecked());
        Jpress.addFunction("METADATA_SELECTED", new MetadataSelected());

        MessageKit.sendMessage(Actions.JPRESS_STARTED);

    }


}
