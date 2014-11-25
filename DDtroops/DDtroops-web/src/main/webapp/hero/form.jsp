<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="h1" name="hero.name"/></th>
        <td><s:text id="h1" name="hero.name"/></td>
    </tr>
    <tr>
        <th><s:label for="h2" name="hero.race"/></th>
        <td><s:text id="h2" name="hero.race"/></td>
    </tr>
    <tr>
        <th><s:label for="h3" name="hero.xp"/></th>
        <td><s:text id="h3" name="hero.xp"/></td>
    </tr>
    <tr>
        <th><s:label for="h4" name="hero.troop"/></th>
        <td>
            <s:select id="h4" name="hero.troop.id">
                <s:option value=""><f:message key="hero.edit.notroop"/></s:option>
                <s:options-collection collection="${actionBean.allTroops}" value="id" label="name" />
            </s:select>
        </td>
    </tr>
    <tr>
        <th><s:label for="h5" name="hero.role"/></th>
        <td>
            <s:select multiple="true" id="h5" name="newRoles">
                <s:options-collection collection="${actionBean.allRoles}" value="id" label="name" />
            </s:select>
        </td>
    </tr>
</table>