## GameDesignTweaks

Plugin for enchancing player experience.

For now only handles how players take damage.

If player takes direct damage from any source:

- while over 75% hp, damage cannot kill the player in 1 hit, it will always leave them at half a heart. This does not work on fall damage.

- while under 75% hp, damage taken is reduced by 20%

- while under 50% hp, damage taken is reduced by 35%

- while under 25% hp, damage taken is reduced by 50%

This is an implementation of a common trick used in games that makes the last percentage of player hp bar "worth" more. This leads to more near death experiences and possibility of satisfying comeback instead of just killing player off.