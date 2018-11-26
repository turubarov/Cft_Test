<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="node() | @*">
		<xsl:copy>
			<xsl:apply-templates select="@* | node()" />
		</xsl:copy>
	</xsl:template>

	<xsl:output method="xml" indent="yes" version="1.0"
		encoding="UTF-8" />
	<xsl:template match="*[*]">
		<xsl:choose>
			<xsl:when test="not(@*)">
				<xsl:copy>
					<xsl:for-each select="*">
						<xsl:choose>
							<xsl:when
								test="not(*) and not(@*) 
		and not(preceding-sibling::*[name() = name(current())]) and not(following-sibling::*[name() 
		= name(current())])">
								<xsl:attribute name="{local-name(.)}"> <xsl:value-of
									select="." /> </xsl:attribute>
							</xsl:when>
							<xsl:otherwise>
								<xsl:apply-templates select="." />
							</xsl:otherwise>
						</xsl:choose>
					</xsl:for-each>
				</xsl:copy>
			</xsl:when>
			<xsl:otherwise>
				<xsl:copy>
					<xsl:apply-templates />
				</xsl:copy>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

</xsl:stylesheet>